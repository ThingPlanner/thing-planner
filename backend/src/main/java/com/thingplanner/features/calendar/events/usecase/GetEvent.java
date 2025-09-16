package com.thingplanner.features.calendar.events.usecase;

import com.thingplanner.features.calendar.events.model.Event;
import com.thingplanner.features.calendar.events.model.EventType;
import com.thingplanner.shared.Response.MessageResponse;
import io.quarkus.panache.common.Parameters;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.time.ZonedDateTime;
import java.util.*;


@Resource
@Path("/events")
class GetEventApi {
    @Inject
    GetEventService getEventService;

    @GET
    @Path("/get")
    public Response getEvent(@Valid GetEventRequest request) {
        try {
            List<GetEventResponse> response = getEventService.get(request);
            return Response.status(200)
                    .entity(response)
                    .build();
        } catch (RuntimeException e) {
            return Response.status(404)
                    .entity(new MessageResponse("Failure", "Could not get events by specified criteria."))
                    .build();
        }
    }

    @GET
    @Path("/get{id}")
    public Response getEventById(@PathParam("id") UUID id) {
        try {
            GetEventResponse response = getEventService.getById(id);
            return Response.status(200)
                    .entity(response)
                    .build();
        } catch (RuntimeException e) {
            return Response.status(500)
                    .entity(new MessageResponse(
                            "Failure", String.format("Could not get event with ID: %s", id)))
                    .build();
        }
    }
}


record GetEventRequest (
        @NotNull(message = "Event id cannot be null")
        @Size(min = 36, message = "Event id must be UUID")
        UUID id,
        String name,
        EventType eventType,
        ZonedDateTime startDateTime,
        ZonedDateTime endDateTime
) {};


record GetEventResponse (
        UUID id,
        String name,
        EventType eventType,
        ZonedDateTime startDateTime,
        ZonedDateTime endDateTime
) {};


@ApplicationScoped
class GetEventService {
    public List<GetEventResponse> get(GetEventRequest request) {
        Event event = new Event();
        StringBuilder query = new StringBuilder("FROM Event e WHERE 1=1");
        Parameters params = null;

        if (request.id() != null) {
            query.append(" AND e.id = :id");
            params = addParameter(params, "id", request.id());
        }

        if (request.name() != null) {
            query.append("AND e.name = :name");
            params = addParameter(params, "name", request.name());
        }

        if (request.eventType() != null) {
            query.append("AND e.eventType = :eventType");
            params = addParameter(params, "eventType", request.eventType());
        }

        if (request.startDateTime() != null) {
            query.append("AND e.startDateTime = :startDateTime");
            params = addParameter(params, "startDateTime", request.startDateTime());
        }

        if (request.endDateTime() != null) {
            query.append("AND e.endDateTime = :endDateTime");
            params = addParameter(params, "endDateTime", request.endDateTime());
        }

        List<Event> events = event.findAllEvents(query.toString(), params);

        return events.stream()
                .map(e -> new GetEventResponse(
                        e.id,
                        e.name,
                        e.eventType,
                        e.startDateTime,
                        e.endDateTime))
                .toList();
    }

    public GetEventResponse getById(UUID id) {
        return Event.findEventByIdOptional(id)
                .map(event -> new GetEventResponse(
                        event.id,
                        event.name,
                        event.eventType,
                        event.startDateTime,
                        event.endDateTime
                ))
                .orElseThrow(() -> new NotFoundException("Event not found with ID: " + id));
    }

    private Parameters addParameter(Parameters params, String name, Object value) {
        return params == null ? Parameters.with(name, value) : params.and(name, value);
    }
}

