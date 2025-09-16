package com.thingplanner.features.organization.model;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "organization")
public class Organization {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;

    @Column(name = "name")
    public String name;

}
