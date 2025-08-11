package com.thingplanner.backend.events.adapter.api.dto.response;

public record ErrorResponse (
        String message,
        Exception exception
) implements EventApiResponse {}
