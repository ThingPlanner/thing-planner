package com.thingplanner.features.pages.usecase;


import com.thingplanner.features.pages.model.Page;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@Resource
@Path("/pages/breadcrumb")
class GetBreadcrumbAPI {

    @Path("/get")
    public List<GetBreadcrumbResponse> getBreadcrumbResponse(GetBreadcrumbRequest request) {

    }
}


record GetBreadcrumbRequest(
        UUID organizationId,
        UUID thingId
) {}


record GetBreadcrumbResponse(
        Long id,
        UUID organizationId,
        UUID thingId,
        String title,
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
