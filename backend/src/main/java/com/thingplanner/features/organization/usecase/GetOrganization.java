package com.thingplanner.features.organization.usecase;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.thingplanner.features.organization.model.Organization;
import com.thingplanner.shared.response.MessageResponse;
import io.quarkus.panache.common.Parameters;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
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

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get")
    public Response getOrganization(GetOrganizationRequest request) {
        try {
            List<GetOrganizationResponse> response = service.get(request);
            return Response.status(200)
                    .entity(response)
                    .build();
        } catch (RuntimeException e) {
            return Response.status(404)
                    .entity(new MessageResponse("Failure", "Could not get organizations by specified criteria"))
                    .build();
        }
    }
}

record GetOrganizationRequest (UUID id, String name) {}

record GetOrganizationResponse (UUID id, String name) {}

@ApplicationScoped
class GetOrganizationService {

    public GetOrganizationResponse getById(UUID id) {
        return Organization.<Organization>findByIdOptional(id)
                .map(organization -> new GetOrganizationResponse(
                        organization.id,
                        organization.name
                ))
                .orElse(null);
    }

    public List<GetOrganizationResponse> get(GetOrganizationRequest request) {

        StringBuilder query = new StringBuilder("FROM Organization o WHERE 1=1");
        Parameters params = null;

        if (request.id() != null) {
            query.append(" AND o.id = :id");
            params = addParameter(params, "id", request.id());
        }

        if (request.name() != null) {
            query.append(" AND o.name = :name");
            params = addParameter(params, "name", request.name());
        }

        List<Organization> organizations = Organization.findAllOrganizations(query.toString(), params);

        return organizations.stream()
                .map(o -> new GetOrganizationResponse(
                        o.id,
                        o.name
                ))
                .toList();
    }

    private Parameters addParameter(Parameters params, String name, Object value) {
        return params == null ? Parameters.with(name, value) : params.and(name, value);
    }
}