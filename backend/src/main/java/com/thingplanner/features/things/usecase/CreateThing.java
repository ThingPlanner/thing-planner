package com.thingplanner.features.things.usecase;

import com.thingplanner.features.things.model.Thing;
import com.thingplanner.shared.response.MessageResponse;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Resource
@Path("/thing")
class CreateThingApi {

    @Inject CreateThingService service;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createThing(CreateThingRequest request) {
        boolean isCreated = service.create(request);

        if (isCreated) {
            return Response.status(200)
                    .entity(new MessageResponse("Success", "New Thing created."))
                    .build();
        } else {
            return Response.status(409)
                    .entity(new MessageResponse("Failure", "Could not create Thing."))
                    .build();
        }
    }
}

record CreateThingRequest (String name) {}

@ApplicationScoped
class CreateThingService {}
