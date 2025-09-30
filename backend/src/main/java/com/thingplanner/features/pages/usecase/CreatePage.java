package com.thingplanner.features.pages.usecase;

import com.thingplanner.features.organization.model.Organization;
import com.thingplanner.features.pages.model.Page;
import com.thingplanner.features.things.model.Thing;
import io.quarkus.logging.Log;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Resource
@Path("/pages")
class CreatePageApi {

    @Inject CreatePageService createPageService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/create")
    public Response createPage(CreatePageRequest request) {
            boolean created = createPageService.createPage(request);

            if (created) {
                return Response.status(201)
                        .entity("New page created successfully.")
                        .build();
            } else {
                return Response.status(424)
                        .entity("Unable to create new page.")
                        .build();
            }
    }
}


record CreatePageRequest (
        UUID id,
        String title,
        Organization organization,
        Thing thing,
        Page parent
) {}

record CreatePageResponse (

) {}


@ApplicationScoped
class CreatePageService {

    @Transactional
    public boolean create(CreatePageRequest request) {
        Page page = new Page();
        page.title = request.title();
        Organization organization = Organization.findById(request.organizationId());
        if (organization.isPersistent()) {
            page.organization = organization;
        }

        Thing thing = Thing.findById(request.thingId());
        if (thing.isPersistent()) {
            page.thing = thing;
        }

        Page parentPage = null;

        if (request.parentId() != null) {
            try {
                parentPage = Page.findById(request.parentId());
            } catch (NotFoundException e) {
                Log.error("Could not find parent page with ID: " + request.parentId() + ", setting parentId to null.");
            }
        }

        page.parent = parentPage;

        try {
            page.persistAndFlush();
            return true;
        } catch (Exception e) {
            Log.error("Unable to persist new page: " + page.id);
            return false;
        }
    }
}
