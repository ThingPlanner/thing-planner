package com.thingplanner.backend.events.core.service;

import com.thingplanner.backend.events.adapter.api.dto.request.EventRequest;
import com.thingplanner.backend.events.adapter.api.dto.response.EventResponse;
import com.thingplanner.backend.events.bootstrap.mapper.EventMapper;
import com.thingplanner.backend.events.core.filter.EventFilter;
import com.thingplanner.backend.events.core.model.EventModel;
import com.thingplanner.backend.events.core.port.inbound.EventServicePort;
import com.thingplanner.backend.events.core.port.outbound.EventRepositoryPort;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class EventServiceImpl implements EventServicePort {

    private final EventRepositoryPort eventRepositoryPort;
    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepositoryPort eventRepositoryPort, EventMapper eventMapper ) {
        this.eventRepositoryPort = eventRepositoryPort;
        this.eventMapper = eventMapper;
    }

    @Override
    public void create(EventRequest request) {
        return null;
    }

    @Override
    public Set<EventModel> findByFields(EventFilter eventFilter) {
        return eventRepositoryPort.findByFields(eventFilter)
                .orElse(Collections.emptySet());
    }

    @Override
    public void updateEvent(EventFilter eventFilter) {
        eventRepositoryPort.updateEvent(eventFilter);
    }

    @Override
    public void deleteEvent(EventFilter eventFilter) {
        eventRepositoryPort.findEvent(eventFilter);
    }
}
