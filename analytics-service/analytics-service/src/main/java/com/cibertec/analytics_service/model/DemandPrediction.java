package com.cibertec.analytics_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "demand_prediction")
@Data
public class DemandPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "demand_level")
    private String demandLevel;

    private BigDecimal confidence;

    @Column(name = "calculated_at")
    private LocalDateTime calculatedAt;

    
}

