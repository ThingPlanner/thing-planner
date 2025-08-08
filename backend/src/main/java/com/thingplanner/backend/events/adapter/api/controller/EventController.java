package com.thingplanner.backend.events.adapter.api.controller;


import ch.qos.logback.core.encoder.EchoEncoder;
import com.thingplanner.backend.events.adapter.api.dto.request.EventRequest;
import com.thingplanner.backend.events.adapter.api.dto.response.EventResponse;
import com.thingplanner.backend.events.core.service.EventServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventServiceImpl eventService;

    public EventController(EventServiceImpl eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEvent(@RequestBody EventRequest request) {
        try {
            eventService.create(request);
        } catch (Exception e) {
            throw new RuntimeException();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Failed to create event.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("New event created.");

    }

    @PostMapping("/get")
    public ResponseEntity<EventResponse> getEvent(@RequestBody EventRequest request) {
        try {
            Long id = request.id();
            EventResponse response = eventService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get event.");
        }
    }
}
