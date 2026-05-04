package com.example.Hotels_DWBI.oltp.model;

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

    public Integer getReservationServiceId() {
        return reservationServiceId;
    }

    public void setReservationServiceId(Integer reservationServiceId) {
        this.reservationServiceId = reservationServiceId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPriceAtBooking() {
        return unitPriceAtBooking;
    }

    public void setUnitPriceAtBooking(BigDecimal unitPriceAtBooking) {
        this.unitPriceAtBooking = unitPriceAtBooking;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }
}
