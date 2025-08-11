package com.thingplanner.backend.events.adapter.api.dto.response;

public record EventTypeErrorResponse(
        String message,
        String details
) implements EventTypeApiResponse {}
