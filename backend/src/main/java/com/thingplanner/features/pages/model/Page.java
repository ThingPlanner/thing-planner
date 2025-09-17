package com.thingplanner.features.pages.model;


import com.thingplanner.features.things.model.Thing;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Parameters;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table (name = "pages")
public class Page extends PanacheEntityBase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public UUID id;

    @Column(name = "title")
    public String title;

    @ManyToOne
    @JoinColumn(name = "thing_id")
    public Thing thing;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    public Page parent;

    @Column(name = "url")
    public String url;

    public static Page findById(UUID id) {
        return PanacheEntityBase.find("id", id).firstResult();
    }

    public static Page findPageByIdOptional(UUID id) {
        return Page.findById(id);
    }

    public static Page findPage(String query, Parameters params) {
        return find(query, params).firstResult();
    }

    public static List<Page> findPages(String query, Parameters params) {
        return list(query, params);
    }

}
