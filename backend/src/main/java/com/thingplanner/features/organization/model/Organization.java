package com.thingplanner.features.organization.model;


import com.thingplanner.features.calendar.events.model.Event;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Parameters;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "organizations")
public class Organization extends PanacheEntityBase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;

    @Column(name = "name")
    public String name;


    public static Optional<Organization> findOrgByIdOptional(UUID id) {
        return findByIdOptional(id);
    }

    public static List<Organization> findAllOrganizations(String query, Parameters params) {
        return list(query, params);
    }
}
