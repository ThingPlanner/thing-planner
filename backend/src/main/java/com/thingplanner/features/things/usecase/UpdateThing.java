package com.thingplanner.features.things.usecase;


import com.thingplanner.features.things.model.Thing;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Resource
@Path("/thing")
class UpdateThingApi {

    @Inject UpdateThingService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updateThing(UpdateThingRequest request) {
        boolean isUpdated = service.update(request);

        if (isUpdated) {
            return Response.status(200)
                    .entity("Organization updated successfully.")
                    .build();
        } else {
            return Response.status(409)
                    .entity("Could not update organization.")
                    .build();
        }
    }

}

record UpdateThingRequest (@NotNull UUID id, String name) {}

record UpdateThingResponse () {}

@ApplicationScoped
class UpdateThingService {
    public boolean update(UpdateThingRequest request) {

        if (!exists(request.id())) {
            return false;
        }

        try {
            Thing thing = new Thing();
            if (!request.name().isBlank() || !request.name().isEmpty()) {
                thing.name = request.name();
            }
            thing.persistAndFlush();
        } catch (Exception e) {
            throw new RuntimeException("Could not persist Thing.");
        }
        return true;
    }

    private boolean exists(UUID id) {
        return Thing.<Thing>findByIdOptional(id)
                .isPresent();
    }

}
