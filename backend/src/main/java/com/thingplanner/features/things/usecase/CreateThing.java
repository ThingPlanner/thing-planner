package com.thingplanner.features.things.usecase;

import com.thingplanner.shared.response.MessageResponse;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Resource
@Path("/thing")
class CreateThingApi {}

record CreateThingRequest () {}

@ApplicationScoped
class CreateThingService {}
