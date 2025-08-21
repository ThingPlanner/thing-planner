package com.thingplanner.backend.events.feature;


import com.thingplanner.backend.events.model.EventRepository;
import com.thingplanner.backend.events.model.EventType;
import com.thingplanner.backend.shared.api.dto.response.MessageResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.UUID;

import static com.thingplanner.backend.events.model.EventSpecification.getEventSpec;

@RestController
@RequestMapping("/events")
class GetEventApi {
    private final GetEventService getEventService;


    public GetEventApi(GetEventService getEventService) {
        this.getEventService = getEventService;
    }

    @RequestMapping("/get")
    public ResponseEntity<?> getEvent(@Valid @RequestBody GetEventRequest request) {
        try {
            return getEventService.get(request);
        } catch (RuntimeException e) {
            return new MessageResponse("Failure", "Could not get events by specified criteria");
        }
    }

    @RequestMapping("/get{id}")
    public getEventById(@RequestParam UUID id) {
        try {
            return getEventService.getById(id);
        } catch (RuntimeException e) {
            return new MessageResponse("Failure", String.format("Could not get event with ID: %s", id));
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

@Service
class GetEventService {
    private final EventRepository eventRepository;

    public GetEventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public GetEventResponse get(GetEventRequest request) {
        var spec = getEventSpec(
                request.id(),
                request.name(),
                request.eventType().getId(),
                request.eventType().getName(),
                request.startDateTime(),
                request.endDateTime()
        );

        eventRepository.findAll(spec))
    }

    public GetEventResponse getById(UUID id) {
        return eventRepository.findOne(id);
    }
}