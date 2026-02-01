package com.cibertec.event_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "event_categories")
@Data
public class EventCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    
}

