package com.thingplanner.features.pages.usecase;


import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Path;

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

}
