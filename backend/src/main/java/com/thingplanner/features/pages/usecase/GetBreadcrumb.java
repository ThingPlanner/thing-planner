package com.thingplanner.features.pages.usecase;


import com.thingplanner.features.pages.model.Page;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Resource
@Path("/pages/breadcrumb")
class GetBreadcrumbAPI {

    @Inject GetBreadcrumbService getBreadcrumbService;

    @Path("/get{pageId}")
    public Response getBreadcrumbResponse(@PathParam("pageId") Long pageId ) {
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


record GetBreadcrumbRequest(
        UUID organizationId,
        UUID thingId
) {}


record GetBreadcrumbResponse(
        Long id,
        String title,
        UUID thingId,
        Long parentId,
        String url
) {}


@ApplicationScoped
class GetBreadcrumbService {

    public List<GetBreadcrumbResponse> getBreadcrumb(Long pageId) {
        List<GetBreadcrumbResponse> breadcrumb = new ArrayList<>();

        Page current = Page.findById(pageId);

        while (current != null) {
            breadcrumb.addFirst(new GetBreadcrumbResponse(
                    current.id,
                    current.title,
                    current.thingId,
                    current.parentId,
                    current.url
            ));

            if (current.parentId != null) {
                current = Page.findById(current.parentId);
            } else {
                current = null;
            }
        }

        return breadcrumb;
    }
}
