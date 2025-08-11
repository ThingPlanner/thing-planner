package com.thingplanner.backend.events.bootstrap.mapper;

import com.thingplanner.backend.events.adapter.api.dto.request.EventRequest;
import com.thingplanner.backend.events.adapter.api.dto.response.EventResponse;
import com.thingplanner.backend.events.adapter.data.entity.EventEntity;
import com.thingplanner.backend.events.core.filter.EventFilter;
import com.thingplanner.backend.events.core.model.EventModel;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface EventMapper {
    EventEntity modelToEntity(EventModel model);
    EventModel entityToModel(EventEntity entity);
    EventModel requestToModel(EventRequest request);
    EventFilter requestToFilter(EventRequest request);
    EventResponse modelToResponse(EventModel eventModel);
    EventResponse modelToResponse(Set<EventModel> eventModel);
}
