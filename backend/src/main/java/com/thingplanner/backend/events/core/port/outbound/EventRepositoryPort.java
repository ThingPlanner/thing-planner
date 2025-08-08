package com.thingplanner.backend.events.core.port.outbound;

import com.thingplanner.backend.events.core.model.EventModel;

import java.util.Optional;
import java.util.Set;

public interface EventRepositoryPort {
    void save(EventModel eventModel);
    Optional<EventModel> findById(Long id);
    Optional<Set<EventModel>> findByFields(EventModel eventModel);
}
