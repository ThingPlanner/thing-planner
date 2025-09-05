package com.thingplanner.features.calendar.events.usecase;

import com.thingplanner.backend.events.model.EventRepository;
import com.thingplanner.backend.shared.api.dto.response.MessageResponse;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;
import java.util.UUID;

@Resource
@Path("/events")
class DeleteEventApi {
    private final DeleteEventService deleteEventService;
    private final EventRepository eventRepository;

    public DeleteEventApi(DeleteEventService deleteEventService, EventRepository eventRepository) {
        this.deleteEventService = deleteEventService;
        this.eventRepository = eventRepository;
    }

    @Path("/delete{id}")
    public ResponseEntity<?> delete(@PathParam UUID id) {
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

@ApplicationScoped
class DeleteEventService {

    public boolean checkEventExists(UUID id) {
        return Event.count("id", id) > 0;
    }

    @Transactional
    public void delete(UUID id) {
        Event.deleteById(id);
    }
}
