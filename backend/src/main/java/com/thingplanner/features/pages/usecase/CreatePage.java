package com.thingplanner.features.pages.usecase;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Path;

@Resource
@Path("/pages")
class CreatePageApi {
}


record CreatePageRequest() {}

record CreatePageResponse() {}


@ApplicationScoped
class CreatePageService {}
