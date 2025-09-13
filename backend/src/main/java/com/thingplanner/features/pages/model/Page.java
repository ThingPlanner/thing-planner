package com.thingplanner.features.pages.model;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table (name = "pages")
public class Page extends PanacheEntityBase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title")
    public String title;

    @ManyToOne
    @Column(name = "thing_id")
    public UUID thingId;

    @ManyToOne
    @Column(name = "parent_id")
    public Long parentId;

    @Column(name = "url")
    public String url;

    public static Page findById(Long id) {
        return PanacheEntityBase.find("id", id).firstResult();
    }

}
