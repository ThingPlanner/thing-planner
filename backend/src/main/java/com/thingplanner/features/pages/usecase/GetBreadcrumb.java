package com.thingplanner.features.pages.usecase;


import com.thingplanner.features.pages.model.Page;
import com.thingplanner.features.things.model.Thing;
import io.quarkus.panache.common.Parameters;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Resource
@Path("/pages/breadcrumb")
class GetBreadcrumbAPI {

    @Inject GetBreadcrumbService getBreadcrumbService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get{pageId}")
    public Response getBreadcrumbResponse(@PathParam("pageId") UUID pageId ) {
        try {
            List<GetBreadcrumbResponse> responses = getBreadcrumbService.getBreadcrumb(pageId);
            return Response.status(200).entity(responses).build();
        } catch (Exception e) {
            return Response.status(404)
                    .entity("Couldn't get breadcrumb for page: " + pageId)
                    .build();
        }
    }
}


record GetBreadcrumbResponse(
        UUID id,
        String title,
        Thing thing,
        Page parent,
        String url
) {}


@ApplicationScoped
class GetBreadcrumbService {

    public List<GetBreadcrumbResponse> getBreadcrumb(UUID pageId) {
        List<GetBreadcrumbResponse> breadcrumb = new ArrayList<>();

        Page current = Page.findPage("id = ?1", Parameters.with("id", pageId));

        while (current != null) {
            breadcrumb.addFirst(new GetBreadcrumbResponse(
                    current.id,
                    current.title,
                    current.thing,
                    current.parent,
                    current.url
            ));

            if (current.parent.id != null) {
                current = Page.findById(current.parent.id);
            } else {
                current = null;
            }
        }

        return breadcrumb;
    }
}
