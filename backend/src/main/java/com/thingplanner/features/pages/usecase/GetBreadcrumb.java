package com.thingplanner.features.pages.usecase;


import com.thingplanner.features.organization.model.Organization;
import com.thingplanner.features.pages.model.Page;
import com.thingplanner.features.things.model.Thing;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;

@Resource
@Path("/pages/breadcrumb")
class GetBreadcrumbAPI {

    @Inject GetBreadcrumbService getBreadcrumbService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{pageId}")
    public Response getBreadcrumbResponse(@PathParam("pageId") UUID pageId ) {
        try {
            Stack<GetBreadcrumbResponse> responses = getBreadcrumbService.getBreadcrumb(pageId);
            return Response.status(200).entity(responses).build();
        } catch (Exception e) {
            return Response.status(404)
                    .entity("Couldn't get breadcrumb for page: " + pageId)
                    .build();
        }
    }
}

record GetBreadcrumbResponse(UUID id, String title, Organization organization,
        Thing thing, Page parent) {}

@ApplicationScoped
class GetBreadcrumbService {

    public Stack<GetBreadcrumbResponse> getBreadcrumb(UUID pageId) {
        if (!exists(pageId)) {
            return null;
        }

        Stack<GetBreadcrumbResponse> breadcrumbTrail = new Stack<>();
        Optional<Page> pageOptional = Page.<Page>findByIdOptional(pageId);

        while (pageOptional.isPresent()) {
            Page currentPage = pageOptional.get();

            GetBreadcrumbResponse breadcrumb = new GetBreadcrumbResponse(
                    currentPage.id,
                    currentPage.title,
                    currentPage.organization,
                    currentPage.thing,
                    currentPage.parent
            );

            breadcrumbTrail.push(breadcrumb);

            if (currentPage.parent != null) {
                pageOptional = Page.findByIdOptional(currentPage.parent.id);
            } else {
                break;
            }
        }

        return breadcrumbTrail;
    }

    private boolean exists(UUID id) {
        return Page.count("id", id) > 0;
    }
}
