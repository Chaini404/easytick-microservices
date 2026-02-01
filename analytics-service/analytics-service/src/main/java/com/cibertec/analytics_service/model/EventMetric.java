package com.cibertec.analytics_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_metrics")
@Data
public class EventMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id")
    private Long eventId;

    @Column(name = "total_sales")
    private Integer totalSales;

    @Column(name = "total_revenue")
    private BigDecimal totalRevenue;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    // getters y setters
}

