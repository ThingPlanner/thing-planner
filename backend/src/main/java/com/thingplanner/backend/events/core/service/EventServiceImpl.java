package com.thingplanner.backend.events.core.service;

import com.thingplanner.backend.events.adapter.api.dto.request.EventRequest;
import com.thingplanner.backend.events.adapter.api.dto.response.EventResponse;
import com.thingplanner.backend.events.core.port.inbound.EventServicePort;

public class EventServiceImpl implements EventServicePort {

    @Override
    public void create(EventRequest request) {
        return null;
    }

    @Override
    public EventResponse findByFields(EventRequest request) {
        return null;
    }

    @Override
    public EventResponse findById(EventRequest request) {
        return null;
    }

    @Override
    public EventResponse search(EventRequest request) {
        return null;
    }

    @Override
    public void update(EventRequest request) {
        return null;
    }

    @Override
    public void delete(EventRequest request) {
        return null;
    }
}
