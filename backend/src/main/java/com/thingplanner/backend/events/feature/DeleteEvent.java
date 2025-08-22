package com.thingplanner.backend.events.feature;


import com.thingplanner.backend.events.model.EventRepository;
import com.thingplanner.backend.shared.api.dto.response.MessageResponse;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/events")
class DeleteEventApi {
    private final DeleteEventService deleteEventService;
    private final EventRepository eventRepository;

    public DeleteEventApi(DeleteEventService deleteEventService, EventRepository eventRepository) {
        this.deleteEventService = deleteEventService;
        this.eventRepository = eventRepository;
    }

    @RequestMapping("/delete{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (eventRepository.existsById(id)) {
            deleteEventService.delete(id);
            return ResponseEntity.status(OK)
                    .body(new MessageResponse("Success", "Event deleted."));
        }
        return ResponseEntity.status(NOT_FOUND)
                .body(new MessageResponse("Failure", "Could not delete event, does not exist."));
    }

    //@RequestMapping("/delete-bulk")
    //public ResponseEntity<?> deleteBulk(@RequestBody BulkDeleteRequest request) {
    //
    //}
}


record BulkDeleteRequest(List<UUID> idsToDelete) {};


@Service
class DeleteEventService {

    private final EventRepository eventRepository;

    DeleteEventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public void delete(UUID id) {
        eventRepository.deleteById(id);
    }
}