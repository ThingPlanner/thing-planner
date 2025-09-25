package com.thingplanner.features.things.usecase;

import com.thingplanner.features.things.model.Thing;
import com.thingplanner.shared.response.MessageResponse;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jdk.jfr.Category;

import java.util.UUID;

@Resource
@Path("/thing")
class DeleteThingApi {

    @Inject DeleteThingService service;

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete{id}")
    public Response deleteThing(@PathParam("id") UUID id) {
        boolean isDeleted = service.delete(id);

        if (isDeleted) {
            return Response.status(204)
                    .entity(new MessageResponse("Success", "Thing deleted."))
                    .build();
        } else {
            return Response.status(404)
                    .entity(new MessageResponse("Failure", "Unable to delete Thing with ID: " + id))
                    .build();
        }
    }
}

@ApplicationScoped
class DeleteThingService {

    public boolean delete(UUID id) {
        if (exists(id)) {
            Thing.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private boolean exists(UUID id) {
        return Thing.count("id", id) > 0;
    }
}
