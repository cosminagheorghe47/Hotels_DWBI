package com.example.Hotels_DWBI.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "RESERVATION_SERVICES")
@Data
public class ReservationService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_service_id", nullable = false)
    private Integer reservationServiceId;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_price_at_booking")
    private BigDecimal unitPriceAtBooking;

    @Column(name = "line_total")
    private BigDecimal lineTotal;
}
