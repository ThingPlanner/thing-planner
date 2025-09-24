package com.thingplanner.features.organization.usecase;

import com.thingplanner.features.organization.model.Organization;
import com.thingplanner.shared.response.MessageResponse;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Resource
@Path("/organization")
class GetOrganizationApi {

    @Inject GetOrganizationService service;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get{id}")
    public Response getOrganizationById(@PathParam("id") UUID id) {
        GetOrganizationResponse response = service.getById(id);
        if (response != null) {
            return Response.status(200)
                    .entity(response)
                    .build();
        } else {
            return Response.status(404)
                    .entity("Could not find response with ID: " + id)
                    .build();
        }
    }

}
