package com.thingplanner.features.pages.usecase;


import com.thingplanner.features.organization.model.Organization;
import com.thingplanner.features.pages.model.Page;
import com.thingplanner.features.things.model.Thing;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.findByIdOptional;


@Resource
@Path("/pages")
class UpdatePageApi {

    @Inject UpdatePageService updatePageService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updatePage(UpdatePageRequest request) {
        boolean isUpdated = updatePageService.updatePage(request);
        if (!isUpdated) {
            return Response.status(404)
                    .entity("Could not update page details.")
                    .build();
        }

        return Response.status(200)
                .entity("Page details updated successfully.")
                .build();
    }

}


record UpdatePageRequest (
        @NotNull
        UUID id,
        String title,
        Organization organization,
        Thing thing,
        Page parent,
        String url
) {}


@ApplicationScoped
class UpdatePageService {

}
