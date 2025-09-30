package com.thingplanner.features.pages.usecase;

import com.thingplanner.features.organization.model.Organization;
import com.thingplanner.features.pages.model.Page;
import com.thingplanner.features.things.model.Thing;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Resource
@Path("/pages")
class GetPageApi {

    @Inject GetPageService getPageService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get{pageId}")
    public Response getPageById(@PathParam("pageId") UUID pageId) {
        try {
            GetPageResponse response = getPageService.getPageById(pageId);
            return Response.status(200)
                    .entity(response)
                    .build();
        } catch (Exception e) {
            return Response.status(404)
                    .entity("Could not find page with ID: " + pageId)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get")
    public Response getPages(GetPageRequest request) {
        try {
            List<GetPageResponse> responses = getPageService.getPages(request);
            return Response.status(200)
                    .entity(responses)
                    .build();
        } catch (Exception e) {
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
        Organization organization,
        Thing thing,
        Page parent
) {}


@ApplicationScoped
class GetPageService {

    public GetPageResponse getPageById(UUID pageId) {
        Page page = Page.findById(pageId);

        return new GetPageResponse(
                page.id,
                page.title,
                page.organization,
                page.thing,
                page.parent
        );
    }

    public List<GetPageResponse> getPages(GetPageRequest request) {
        List<Page> pages = Page.find(
                "organization.id = ?1 AND thing.id = ?2",
                request.organizationId(), request.thingId()).list();

        return pages.stream()
                .map(p -> new GetPageResponse(
                        p.id,
                        p.title,
                        p.organization,
                        p.thing,
                        p.parent
                )).toList();
    }
}