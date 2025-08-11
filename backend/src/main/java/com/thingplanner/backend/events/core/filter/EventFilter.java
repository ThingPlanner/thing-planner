package com.thingplanner.backend.events.core.filter;

import com.thingplanner.backend.events.adapter.api.dto.request.EventTypeRequest;

import java.time.ZonedDateTime;

public record EventFilter(
        Long id,
        String name,
        EventTypeRequest eventType,
        ZonedDateTime startDateTime,
        ZonedDateTime endDateTime
) {}
