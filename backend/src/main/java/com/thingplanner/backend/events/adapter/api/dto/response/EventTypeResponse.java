package com.thingplanner.backend.events.adapter.api.dto.response;

public record EventTypeResponse(
        Long id,
        String name
) implements EventTypeApiResponse {}
