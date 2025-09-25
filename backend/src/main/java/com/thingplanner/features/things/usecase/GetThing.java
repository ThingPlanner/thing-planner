package com.thingplanner.features.things.usecase;


import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Path;

@Resource
@Path("/thing")
class GetThingApi {}

record GetThingRequest () {}

record GetThingResponse() {}

@ApplicationScoped
class GetThingService {}
