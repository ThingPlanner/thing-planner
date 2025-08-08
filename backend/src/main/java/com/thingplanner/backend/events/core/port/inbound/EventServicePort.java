package com.thingplanner.backend.events.core.port.inbound;

import com.thingplanner.backend.events.adapter.api.dto.request.EventRequest;
import com.thingplanner.backend.events.adapter.api.dto.response.EventResponse;

public interface EventServicePort {
    void create(EventRequest request);
    EventResponse findByFields(EventRequest request);
    EventResponse findById(EventRequest request);
    EventResponse search(EventRequest request);
    void update(EventRequest request);
    void delete(EventRequest request);
}
