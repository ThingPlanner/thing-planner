package com.thingplanner.features.organization.usecase;


import com.thingplanner.features.organization.model.Organization;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Resource
@Path("/organization")
class UpdateOrganizationApi {

    @Inject UpdateOrganizationService service;

    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrganization(UpdateOrganizationRequest request) {
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

@ApplicationScoped
class UpdateOrganizationService {
    public boolean update(UpdateOrganizationRequest request) {

        if (!exists(request.id())) {
            return false;
        }

        try {
            Organization organization = new Organization();
            if (!request.name().isBlank() || !request.name().isEmpty()) {
                organization.name = request.name();
            }
            organization.persistAndFlush();
        } catch (Exception e) {
            throw new RuntimeException("Could not persist Organization.");
        }
        return true;
    }

}
