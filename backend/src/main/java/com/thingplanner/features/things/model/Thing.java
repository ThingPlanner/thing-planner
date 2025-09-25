package com.thingplanner.features.things.model;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "things")
public class Thing extends PanacheEntityBase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;

    @Column(name = "name")
    public String name;

}
