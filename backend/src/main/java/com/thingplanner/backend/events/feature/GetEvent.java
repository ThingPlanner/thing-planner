package com.thingplanner.backend.events.feature;


import com.thingplanner.backend.events.model.EventRepository;
import com.thingplanner.backend.events.model.EventType;
import com.thingplanner.backend.shared.api.dto.response.MessageResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
            return ResponseEntity.status(HttpStatus.OK)
                    .body(getEventService.get(request));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Failure", "Could not get events by specified criteria."));
        }
    }

    @RequestMapping("/get{id}")
    public ResponseEntity<?> getEventById(@PathVariable UUID id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(getEventService.getById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse(
                            "Failure", String.format("Could not get event with ID: %s", id)));
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

    public List<GetEventResponse> get(GetEventRequest request) {
        var spec = getEventSpec(
                request.id(),
                request.name(),
                request.eventType().getId(),
                request.eventType().getName(),
                request.startDateTime(),
                request.endDateTime()
        );

        return eventRepository.findAll()
                .stream()
                .map(event -> new GetEventResponse(
                        event.getId(),
                        event.getName(),
                        event.getEventType(),
                        event.getStartDateTime(),
                        event.getEndDateTime()
                ))
                .collect(Collectors.toList());
    }

    public Optional<GetEventResponse> getById(UUID id) {
        return eventRepository.findById(id)
                .map(event -> new GetEventResponse(
                        event.getId(),
                        event.getName(),
                        event.getEventType(),
                        event.getStartDateTime(),
                        event.getEndDateTime()
                ));
    }
}