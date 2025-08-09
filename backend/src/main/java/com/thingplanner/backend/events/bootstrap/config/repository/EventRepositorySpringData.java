package com.thingplanner.backend.events.bootstrap.config.repository;

import com.thingplanner.backend.events.adapter.data.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EventRepositorySpringData
        extends JpaRepository<EventEntity, Long>, JpaSpecificationExecutor<EventEntity> {}
