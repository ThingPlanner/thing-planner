package com.thingplanner.backend.events.adapter.api.dto.response;

public sealed interface EventApiResponse permits EventResponse, ErrorResponse {}
