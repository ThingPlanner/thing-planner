package com.thingplanner.features.organization.usecase;

import com.thingplanner.features.organization.model.Organization;
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
@Path("/organization")
class CreateOrganizationApi {

    @Inject CreateOrganizationService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response createOrganization(CreateOrganizationRequest request) {
        boolean isCreated = service.create(request);
        if (isCreated) {
            return Response.status(201)
                    .entity("Organization created successfully!")
                    .build();
        } else {
            return Response.status(424)
                    .entity("Unable to create Organization")
                    .build();
        }
    }
}

record CreateOrganizationRequest (
        String name
) {}

@ApplicationScoped
class CreateOrganizationService {
    public boolean create(CreateOrganizationRequest request) {
        try {
            Organization organization = new Organization();
            organization.name = request.name();
            Organization.persist(organization);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
