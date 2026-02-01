package com.cibertec.event_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.cibertec.event_service.model.type.EventStatus;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "event_date")
    private LocalDateTime eventDate;

    private String location;

    private BigDecimal price;

    private Integer capacity;

    @Column(name = "available_slots")
    private Integer availableSlots;

    @Column(name = "organizer_id")
    private Long organizerId;

    @Enumerated(EnumType.STRING)
    private EventStatus eventStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    
}

