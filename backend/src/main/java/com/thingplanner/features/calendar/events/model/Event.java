package com.thingplanner.features.calendar.events.model;

import com.sun.jdi.InvalidTypeException;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;


@Entity
@Table(name = "events")
public class Event extends PanacheEntityBase {

    @Id
    public UUID id;

    @Column(name = "name")
    public String name;

    @ManyToOne
    @JoinColumn(name = "event_type", referencedColumnName = "id")
    public EventType eventType;

    @Column(name = "start_date_time")
    public ZonedDateTime startDateTime;

    @Column(name = "end_date_time")
    public ZonedDateTime endDateTime;

    public Event() {}


}