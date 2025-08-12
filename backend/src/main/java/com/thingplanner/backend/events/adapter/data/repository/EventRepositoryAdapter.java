package com.thingplanner.backend.events.adapter.data.repository;

import com.sun.jdi.request.EventRequest;
import com.thingplanner.backend.events.adapter.data.entity.EventEntity;
import com.thingplanner.backend.events.bootstrap.config.repository.EventRepositorySpringData;
import com.thingplanner.backend.events.bootstrap.mapper.EventMapper;
import com.thingplanner.backend.events.core.model.EventModel;
import com.thingplanner.backend.events.core.port.outbound.EventRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class EventRepositoryAdapter implements EventRepositoryPort {

    private final EventRepositorySpringData jpaRepository;
    private final EventMapper eventMapper;

    public EventRepositoryAdapter(EventRepositorySpringData jpaRepository, EventMapper eventMapper) {
        this.jpaRepository = jpaRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public void save(EventModel eventModel) {
        EventEntity eventEntity = eventMapper.modelToEntity(eventModel);
        EventEntity eventEntitySaved = jpaRepository.save(eventEntity);
    }

    @Override
    public Optional<EventModel> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Set<EventModel> findEvent(EventFilter eventFilter) {
        Specification<EventEntity> spec = eventSpec(
                eventFilter.id(),
                eventFilter.name(),
                eventFilter.eventType().id(),
                eventFilter.eventType().name(),
                eventFilter.startDateTime(),
                eventFilter.endDateTime()
        );

        return jpaRepository.findAll(spec).stream()
                .map(eventMapper::entityToModel)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Set<EventModel>> findByFields(EventModel eventModel) {
        return Optional.empty();
    }
}
