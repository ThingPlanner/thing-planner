package com.thingplanner.features.calendar.events.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Parameters;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.*;


@Entity
@Table(name = "events")
public class Event extends PanacheEntityBase {

    @Id @GeneratedValue public UUID id;

    @Column(name = "name")
    public String name;

    @ManyToOne
    @JoinColumn(name = "event_type_id")
    public EventType eventType;

    @Column(name = "start_date_time")
    public ZonedDateTime startDateTime;

    @Column(name = "end_date_time")
    public ZonedDateTime endDateTime;

    public static Optional<Event> findEventByIdOptional(UUID id) {
        return findByIdOptional(id);
    }

    public List<Event> findAllEvents(String query, Parameters params) {
        return list(query, params);
    }

}