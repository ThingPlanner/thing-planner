package com.thingplanner.backend.events.adapter.data.repository;

import com.sun.jdi.request.EventRequest;
import com.thingplanner.backend.events.adapter.data.entity.EventEntity;
import com.thingplanner.backend.events.bootstrap.config.repository.EventRepositorySpringData;
import com.thingplanner.backend.events.core.model.EventModel;
import com.thingplanner.backend.events.core.port.outbound.EventRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class EventRepositoryAdapter implements EventRepositoryPort {

    private final EventRepositorySpringData jpaRepository;

    public EventRepositoryAdapter(EventRepositorySpringData jpaRepository) {
        this.jpaRepository = jpaRepository;
    }


    @Override
    public void save(EventModel eventModel) {
        EventEntity eventEntity = EventEntity.fromCore(eventModel);
        EventEntity eventEntitySaved = jpaRepository.save(eventEntity);
        return eventEntitySaved.toCore();
    }

    @Override
    public Optional<EventModel> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Set<EventModel>> findByFields(EventModel eventModel) {
        return Optional.empty();
    }
}
