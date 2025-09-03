package com.thingplanner.features.calendar.events.model;

import com.sun.jdi.InvalidTypeException;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.UUID;


@Entity
@Table(name = "events")
public class Event {

    @Id //@UuidGenerator
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "event_type", referencedColumnName = "id")
    private EventType eventType;

    @Column(name = "start_date_time")
    private ZonedDateTime startDateTime;

    @Column(name = "end_date_time")
    private ZonedDateTime endDateTime;

    public Event() {}

    public Event(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.eventType = builder.eventType;
        this.startDateTime = builder.startDateTime;
        this.endDateTime = builder.endDateTime;
    }

    public static class Builder {
        private UUID id;
        private String name;
        private EventType eventType;
        private ZonedDateTime startDateTime;
        private ZonedDateTime endDateTime;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder eventType(EventType eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder startDateTime(ZonedDateTime startDateTime) {
            this.startDateTime = startDateTime;
            return this;
        }

        public Builder endDateTime(ZonedDateTime endDateTime) {
            this.endDateTime = endDateTime;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setStartDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setEndDateTime(ZonedDateTime endDateTime) throws InvalidTypeException {
        if (endDateTime.isBefore(this.startDateTime)) {
            throw new Error("Event endDateTime cannot be before startDateTime");
        }
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

}