package com.thingplanner.backend.mevents.feature;

import com.thingplanner.backend.mevents.bootstrap.exception.MalformedRequestException;
import com.thingplanner.backend.mevents.model.EventRepository;
import com.thingplanner.backend.mevents.model.Event;
import com.thingplanner.backend.mevents.model.EventType;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/events")
class AddEventApi {
    private final AddService addService;

    public AddEventApi(AddService addService) {
        this.addService = addService;
    }

    @PostMapping("/add")
    @ResponseStatus(CREATED)
    AddEventResponse add(@RequestBody @Valid AddEventRequest request) throws RuntimeException {
        try {
            return addService.add(request);
        } catch (RuntimeException e){
            return new AddEventResponse("Failure", "Could not add new event.");
        }
    }
}

record AddEventRequest(
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

record AddEventResponse (
        String message,
        String details
) {};

@Service
class AddService {
    private final EventRepository eventRepository;

    public AddService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    private Optional<Event> buildEvent(AddEventRequest request) {
        UUID id = UUID.randomUUID();
        var eventType = new EventType.Builder()
                .id(request.eventType().getId())
                .name(request.eventType().getName())
                .build();

        return Optional.ofNullable(new Event.Builder()
                .id(UUID.randomUUID())
                .name(request.name())
                .eventType(eventType)
                .startDateTime(request.startDateTime())
                .endDateTime(request.endDateTime())
                .build());
    }

    public AddEventResponse add(AddEventRequest request) {
        var event = buildEvent(request)
                .orElseThrow(() -> new MalformedRequestException("Could not build event from request."));
        try {
            eventRepository.save(event);
            return new AddEventResponse("Success", "Event created successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Could not save event.", e);
        }
    }
}

