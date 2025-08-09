package com.thingplanner.backend.events.core.filter;

import com.thingplanner.backend.events.adapter.data.entity.EventTypeEntity;

import java.time.ZonedDateTime;

public record EventFilter(
        Long id,
        String name,
        EventTypeEntity eventType,
        ZonedDateTime startDateTime,
        ZonedDateTime endDateTime
) {}
