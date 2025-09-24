package com.thingplanner.features.organization.usecase;

import com.thingplanner.features.calendar.events.model.Event;
import com.thingplanner.features.organization.model.Organization;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;
import java.util.UUID;

@Resource
@Path("/organization")
class DeleteOrganizationApi {

    @Inject DeleteOrganizationService service;

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/organization/delete{id}")
    public Response deleteOrganization(@PathParam("id") UUID organizationId) {
        boolean isDeleted = service.delete(organizationId);
        if (isDeleted) {
            return Response.status(200)
                    .entity("Organization deleted successfully")
                    .build();
        } else {
            return Response.status(409)
                    .entity("Unable to delete organization ID: " + organizationId)
                    .build();
        }
    }
}

@ApplicationScoped
class DeleteOrganizationService {

    public boolean delete(UUID id) {
        if (exists(id)) {
            return Organization.deleteById(id);
        } else {
            return false;
        }
    }

}
