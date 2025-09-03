package com.thingplanner.features.calendar.events.usecase;

import com.sun.jdi.InvalidTypeException;
import com.thingplanner.backend.events.model.Event;
import com.thingplanner.backend.events.model.EventRepository;
import com.thingplanner.backend.events.model.EventType;
import com.thingplanner.backend.shared.api.dto.response.MessageResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/events")
class UpdateEventApi {

    private final UpdateEventService updateEventService;

    public UpdateEventApi(UpdateEventService updateEventService) {
        this.updateEventService = updateEventService;
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateEvent(@RequestBody UpdateEventRequest request) {
        try {
            updateEventService.updateEvent(request);
            return ResponseEntity.status(OK)
                    .body(new MessageResponse("Success", "Event updated."));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Failure", "Could not update event."));
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

@Service
class UpdateEventService {
    private final EventRepository eventRepository;

    public UpdateEventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void updateEvent(UpdateEventRequest request) throws InvalidTypeException {
        var event = eventRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Event not found."));

        try {
            mapFieldsToUpdate(request, event);
        } catch (InvalidTypeException e) {
            throw new InvalidTypeException("Unable to update event.");
        }
        eventRepository.save(event);
    }

    private void mapFieldsToUpdate(UpdateEventRequest request, Event event) throws InvalidTypeException {
        if (!request.name().isBlank() || !request.name().isEmpty()) {
            event.setName(request.name());
        }
        if (!(request.eventType().getId() == null) && !(request.eventType().getName().isBlank())) {
            event.setEventType(request.eventType());
        }
        if (!(request.startDateTime() == null)) {
            event.setStartDateTime(request.startDateTime());
        }
        if (!(request.endDateTime() == null) && !(request.endDateTime().isBefore(event.getStartDateTime()))) {
            event.setEndDateTime(request.endDateTime());
        }
    }
}
