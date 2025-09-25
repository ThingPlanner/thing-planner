package com.thingplanner.features.things.usecase;

import com.thingplanner.shared.response.MessageResponse;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Resource
@Path("/thing")
class CreateThingApi {

    @Inject CreateThingService service;

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

record CreateThingRequest () {}

@ApplicationScoped
class CreateThingService {}
