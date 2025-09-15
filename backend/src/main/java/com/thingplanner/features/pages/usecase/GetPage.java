package com.thingplanner.features.pages.usecase;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Resource
@Path("/pages")
class GetPageApi {

    @Path("/get{pageId}")
    public Response getPageById(@PathParam("pageId", UUID pageId)) {}

    @Path("/get")
    public Response getPages(GetPageRequest request) {}

    }
}



