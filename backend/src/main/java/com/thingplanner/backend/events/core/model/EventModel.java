package com.thingplanner.backend.events.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.time.ZonedDateTime;

@Getter
@Setter
public class EventModel {
    Long id;
    String name;
    EventTypeModel eventType;
    ZonedDateTime startDateTime;
    ZonedDateTime endDateTime;
}
