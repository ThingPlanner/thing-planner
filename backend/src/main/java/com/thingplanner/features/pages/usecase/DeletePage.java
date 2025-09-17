package com.thingplanner.features.pages.usecase;

import com.thingplanner.features.pages.model.Page;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Resource
@Path("/pages")
class DeletePageApi {

    @Inject DeletePageService deletePageService;

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete{id}")
    public Response deletePage(@PathParam("id") UUID id) {
        boolean pageDeleted = deletePageService.deletePage(id);

        if (!pageDeleted) {
            return Response.status(404)
                    .entity("Could not delete page: " + id)
                    .build();
        }

        return Response.status(204)
                .entity("Deleted page: " + id)
                .build();
    }

}


@ApplicationScoped
class DeletePageService {

    @Transactional
    public boolean deletePage(UUID pageId) {
        return Page.deleteById(pageId);
    }
}