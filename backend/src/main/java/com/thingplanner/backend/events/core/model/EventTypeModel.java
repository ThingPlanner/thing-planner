package com.thingplanner.backend.events.core.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
public class EventTypeModel {
    Long id;
    String name;
}
