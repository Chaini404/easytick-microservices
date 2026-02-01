package com.cibertec.booking_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_history")
@Data
public class BookingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_id")
    private Long bookingId;

    private String status;

    @Column(name = "changed_at")
    private LocalDateTime changedAt;

    // getters y setters
}
