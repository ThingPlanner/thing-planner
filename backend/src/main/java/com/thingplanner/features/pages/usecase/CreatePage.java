package com.thingplanner.features.pages.usecase;

import com.thingplanner.features.organization.model.Organization;
import com.thingplanner.features.pages.model.Page;
import com.thingplanner.features.things.model.Thing;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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

    public boolean createPage(CreatePageRequest request) {
        Page page = new Page();
        page.id = UUID.randomUUID();
        page.title = request.title();
        page.organization = request.organization();
        page.thing = request.thing();
        page.parent = request.parent();
        page.url = "/" + request.organization().id + "/" + request.thing().id + "/" + page.id;

        try {
            Page.persist(page);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
