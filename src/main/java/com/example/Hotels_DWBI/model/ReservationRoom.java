package com.example.Hotels_DWBI.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "RESERVATION_ROOMS")
@Data
public class ReservationRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_room_id", nullable = false)
    private Integer reservationRoomId;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "price_per_night")
    private BigDecimal pricePerNight;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    @Column(name = "final_price_per_night")
    private BigDecimal finalPricePerNight;
}
