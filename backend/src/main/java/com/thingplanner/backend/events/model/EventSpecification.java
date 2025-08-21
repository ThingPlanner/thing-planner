package com.thingplanner.backend.events.model;

import org.springframework.data.jpa.domain.Specification;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class EventSpecification {

    public static Specification<Event> eventSpec(
            Long id,
            String name,
            Long eventTypeId,
            String eventTypeName,
            ZonedDateTime startDateTime,
            ZonedDateTime endDateTime
    ) {

        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), id));
            }

            if (name != null && !name.isBlank()) {
                predicates.add(criteriaBuilder.equal(root.get("name"), name));
            }

            if (eventTypeId != null) {
                predicates.add(criteriaBuilder.equal(root.get("eventType").get("id"), eventTypeId));
            }

            if (eventTypeName != null && !eventTypeName.isBlank()) {
                predicates.add(criteriaBuilder.equal(root.get("eventType").get("name"), eventTypeName));
            }

            if (startDateTime != null) {
                predicates.add(criteriaBuilder.equal(root.get("startDateTime"), startDateTime));
            }

            if (endDateTime != null) {
                predicates.add(criteriaBuilder.equal(root.get("endDateTime"), endDateTime));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
