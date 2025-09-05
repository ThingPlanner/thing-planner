package com.thingplanner.features.calendar.events.model;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.UUID;

public interface EventRepository extends PanacheRepositoryBase<Event, UUID> {}
