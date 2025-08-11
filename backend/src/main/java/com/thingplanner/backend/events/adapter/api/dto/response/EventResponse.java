package com.thingplanner.backend.events.adapter.api.dto.response;

import java.time.ZonedDateTime;

public record EventResponse(
        Long id,
        String name,
        EventTypeResponse eventType,
        ZonedDateTime startDateTime,
        ZonedDateTime endDateTime
) implements EventApiResponse {}
