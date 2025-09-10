package com.thingplanner.features.calendar.events.usecase;

import com.thingplanner.features.calendar.events.model.Event;
import com.thingplanner.features.calendar.events.model.EventType;
import com.thingplanner.shared.Response.MessageResponse;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.thingplanner.features.calendar.events.model.EventSpecification.*;

@Resource
@Path("/events")
class GetEventApi {
    @Inject
    GetEventService getEventService;

    @GET
    @Path("/get")
    public Response getEvent(@Valid GetEventRequest request) {
        try {
            GetEventResponse response = getEventService.get(request);
            return Response.status(200)
                    .entity(response)
                    .build();
        } catch (RuntimeException e) {
            return Response.status(500)
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
        var spec = getEventSpec(
                request.id(),
                request.name(),
                request.eventType().getId(),
                request.eventType().getName(),
                request.startDateTime(),
                request.endDateTime()
        );

        return Event.findAll()
                .stream()
                .map(event -> new GetEventResponse(
                        event.id,
                        event.name,
                        event.eventType,
                        event.startDateTime,
                        event.endDateTime
                ))
                .collect(Collectors.toList());
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
}
