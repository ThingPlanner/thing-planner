package com.thingplanner.features.organization.model;


import com.thingplanner.features.calendar.events.model.Event;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "organization")
public class Organization extends PanacheEntityBase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;

    @Column(name = "name")
    public String name;

}
