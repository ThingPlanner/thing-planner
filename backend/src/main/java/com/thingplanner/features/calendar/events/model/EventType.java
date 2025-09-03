package com.thingplanner.features.calendar.events.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "event_types")
public class EventType {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    public EventType() {}
    public EventType(Builder builder) {}

    public static class Builder {
        Long id;
        String name;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public EventType build() {
            return new EventType(this);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
