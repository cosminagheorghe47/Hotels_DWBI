package com.example.Hotels_DWBI.oltp.model;

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

    public Integer getReservationRoomId() {
        return reservationRoomId;
    }

    public void setReservationRoomId(Integer reservationRoomId) {
        this.reservationRoomId = reservationRoomId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalPricePerNight() {
        return finalPricePerNight;
    }

    public void setFinalPricePerNight(BigDecimal finalPricePerNight) {
        this.finalPricePerNight = finalPricePerNight;
    }
}
