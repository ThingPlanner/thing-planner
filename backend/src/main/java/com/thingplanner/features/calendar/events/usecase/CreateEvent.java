package com.thingplanner.features.calendar.events.usecase;

import com.thingplanner.features.calendar.events.bootstrap.exception.MalformedRequestException;
import com.thingplanner.features.calendar.events.model.EventRepository;
import com.thingplanner.features.calendar.events.model.Event;
import com.thingplanner.features.calendar.events.model.EventType;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

import java.time.ZonedDateTime;
import java.util.Optional;

@Resource
@Path("/events")
class CreateEventApi {
    @Inject
    CreateEventService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/add")
    public AddEventResponse add(@Valid AddEventRequest request) throws RuntimeException {
        try {
            return addService.add(request);
        } catch (RuntimeException e){
            return new AddEventResponse("Failure", "Could not add new event.");
        }
    }
}

record CreateEventRequest(
        @NotNull(message = "Event name cannot be null")
        @Size(min = 1, message = "Event name cannot be empty")
        String name,

        @NotNull(message = "Event name cannot be null")
        EventType eventType,

        @NotNull(message = "Event name cannot be null")

        ZonedDateTime startDateTime,

        @NotNull(message = "Event name cannot be null")
        ZonedDateTime endDateTime
) {};

record CreateEventResponse(
        String message,
        String details
) {};

@ApplicationScoped
class CreateEventService {

    private Optional<Event> buildEvent(AddEventRequest request) {
        var id = UUID.randomUUID();

        var event = new Event();
        event.id = id;
        event.name = request.name();
        event.eventType = request.eventType();
        event.startDateTime = request.startDateTime();
        event.endDateTime = request.endDateTime();

        return Optional.of(event);
    }

    @Transactional
    public boolean create(CreateEventRequest request) {
        var event = buildEvent(request)
                .orElseThrow(() -> new MalformedRequestException("Could not build event from request."));
        try {
            event.persistAndFlush();
            return true;
        } catch (Exception e) {
            Log.error("Failed to persist Event entity", e);
            return false;
        }
    }
}
