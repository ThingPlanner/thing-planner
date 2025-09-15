package com.thingplanner.features.pages.usecase;

import com.thingplanner.features.pages.model.Page;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Resource
@Path("/pages")
class GetPageApi {

    @Inject GetPageService getPageService;

    @Path("/get{pageId}")
    public Response getPageById(@PathParam("pageId", UUID pageId)) {}

    @Path("/get")
    public Response getPages(GetPageRequest request) {
        try {
            List<GetPageResponse> responses = getPageService.getPages(request);
            return Response.status(200)
                    .entity(responses)
                    .build();
        } catch (Exception e){
            return Response.status(404)
                    .entity("Could not find pages.")
                    .build();
        }
    }
}


record GetPageRequest (
        @NotNull
        UUID organizationId,
        @NotNull
        UUID thingId,
        UUID pageId
) {}

record GetPageResponse (
        UUID id,
        String title,
        UUID thingId,
        UUID parentId,
        String url
) {}


@ApplicationScoped
class GetPageService {

}