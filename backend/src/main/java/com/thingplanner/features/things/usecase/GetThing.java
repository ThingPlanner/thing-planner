package com.thingplanner.features.things.usecase;


import com.thingplanner.features.things.model.Thing;
import com.thingplanner.shared.response.MessageResponse;
import io.quarkus.runtime.annotations.ConfigDocDefault;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Resource
@Path("/thing")
class GetThingApi {

    @Inject GetThingService service;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get{id}")
    public Response getThingById(@PathParam("id") UUID id) {
        GetThingResponse response = service.getById(id);
        if (response != null) {
            return Response.status(200)
                    .entity(response)
                    .build();
        } else {
            return Response.status(404)
                    .entity(new MessageResponse("Failure", "Could not find Thing with ID: " + id))
                    .build();
        }
    }
}

record GetThingRequest (UUID id, String name) {}

record GetThingResponse(UUID id, String name) {}

@ApplicationScoped
class GetThingService {

    public GetThingResponse getById(UUID id) {
        return Thing.<Thing>findByIdOptional(id)
                .map(thing -> new GetThingResponse(
                        thing.id,
                        thing.name
                        ))
                .orElse(null);
    }
}
