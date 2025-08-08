package com.thingplanner.backend.events.bootstrap.config.repository;

import com.thingplanner.backend.events.adapter.data.entity.EventEntity;

public interface EventRepositorySpringData
        extends JpaRepository<EventEntity, Long>, JpaSpecificationsExecutor<EventEntity> {}
