package com.thingplanner.features.organization.usecase;


@ApplicationScoped
class CreateOrganizationService {
    public boolean create(CreateOrganizationRequest request) {
        try {
            Organization organization = new Organization();
            organization.name = request.name();
            Organization.persist(organization);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
