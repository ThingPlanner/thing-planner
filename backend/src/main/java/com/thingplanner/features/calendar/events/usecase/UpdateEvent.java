package com.thingplanner.features.calendar.events.usecase;

import com.sun.jdi.InvalidTypeException;

import com.thingplanner.features.calendar.events.model.Event;
import com.thingplanner.features.calendar.events.model.EventType;
import com.thingplanner.shared.Response.MessageResponse;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.time.ZonedDateTime;
import java.util.UUID;

import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.findById;
import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.findByIdOptional;

@Resource
@Path("/events")
class UpdateEventApi {

    @Inject
    UpdateEventService updateEventService;

    @POST
    @Path("/update")
    public Response updateEvent(UpdateEventRequest request) {
        try {
            updateEventService.updateEvent(request);
            return Response.status(200)
                    .entity(new MessageResponse("Success", "Event updated."))
                    .build();
        } catch (Exception e) {
            return Response.status(500)
                    .entity(new MessageResponse("Failure", "Could not update event."))
                    .build();
        }
    }
}

record UpdateEventRequest (
        @NotNull
        UUID id,
        String name,
        EventType eventType,
        ZonedDateTime startDateTime,
        ZonedDateTime endDateTime
) {}

record UpdateEventResponse (

) {}

@ApplicationScoped
class UpdateEventService {

    @Transactional
    public void updateEvent(UpdateEventRequest request) throws InvalidTypeException {
        boolean exists = findByIdOptional(request.id())
                .isPresent();

        if (exists) {
            Event event = new Event();
            mapFieldsToUpdate(request, event);
            event.persistAndFlush();
        }
    }

    private void mapFieldsToUpdate(UpdateEventRequest request, Event event) throws InvalidTypeException {
        if (!request.name().isBlank() || !request.name().isEmpty()) {
            event.name = request.name();
        }
        if (!(request.eventType() == null) && !(request.eventType().name.isBlank())) {
            event.eventType = request.eventType();
        }
        if (!(request.startDateTime() == null)) {
            event.startDateTime = request.startDateTime();
        }
        if (!(request.endDateTime() == null) && !(request.endDateTime().isBefore(event.endDateTime))) {
            event.endDateTime = request.endDateTime();
        }
    }
}
