package com.thingplanner.features.calendar.events.usecase;

import com.thingplanner.shared.response.MessageResponse;
import com.thingplanner.features.calendar.events.model.Event;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Resource
@Path("/events")
class DeleteEventApi {

    @Inject
    DeleteEventService deleteEventService;

    @DELETE
    @Path("/delete{id}")
    public Response delete(@PathParam("id") UUID id) {
        if (deleteEventService.checkEventExists(id)) {
            deleteEventService.delete(id);
            return Response.status(200)
                    .entity(new MessageResponse("Success", "Event deleted."))
                    .build();
        }
        return Response.status(404)
                .entity(new MessageResponse("Failure", "Could not delete event, does not exist."))
                .build();
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
