package com.thingplanner.backend.events.adapter.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "event_types")
@NoArgsConstructor
@AllArgsConstructor
public class EventTypeEntity {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

}
