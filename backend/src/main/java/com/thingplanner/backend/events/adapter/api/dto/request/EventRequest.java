package com.thingplanner.backend.events.adapter.api.dto.request;

import java.time.ZonedDateTime;

public record EventRequest(
        Long id,
        String name,
        EventTypeRequest eventType,
        ZonedDateTime startDateTime,
        ZonedDateTime endDateTime
) {}
