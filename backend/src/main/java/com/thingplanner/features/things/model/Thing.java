package com.thingplanner.features.things.model;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "things")
public class Thing {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;

    @Column(name = "name")
    public String name;

}
