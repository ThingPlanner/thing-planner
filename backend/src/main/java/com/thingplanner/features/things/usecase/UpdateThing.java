package com.thingplanner.features.things.usecase;


import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Path;

@Resource
@Path("/thing")
class UpdateThingApi {}

record UpdateThingRequest () {}

record UpdateThingResponse () {}

@ApplicationScoped
class UpdateThingService {}
