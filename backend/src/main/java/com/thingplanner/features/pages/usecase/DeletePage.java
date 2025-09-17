package com.thingplanner.features.pages.usecase;

import com.thingplanner.features.pages.model.Page;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Resource
@Path("/pages")
class DeletePageApi {

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete{id}")
    public Response deletePage(@PathParam("id") UUID id) {
        boolean pageDeleted = Page.deleteById(id);

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

}