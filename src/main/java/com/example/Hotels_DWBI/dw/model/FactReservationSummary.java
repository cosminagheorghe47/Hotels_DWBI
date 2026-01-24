package com.example.Hotels_DWBI.dw.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "FACT_RESERVATION_SUMMARY")
@Data
public class FactReservationSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_key", nullable = false)
    private Integer reservationKey;

    @Column(name = "reservation_id_oltp")
    private Integer reservationIdOltp;

    @Column(name = "reservation_room_id_oltp")
    private Integer reservationRoomIdOltp;

    @Column(name = "hotel_key")
    private Integer hotelKey;

    @Column(name = "guest_key")
    private Integer guestKey;

    @Column(name = "channel_key")
    private Integer channelKey;

    @Column(name = "status_key")
    private Integer statusKey;

    @Column(name = "check_in_date_key")
    private Integer checkInDateKey;

    @Column(name = "check_out_date_key")
    private Integer checkOutDateKey;

    @Column(name = "created_date_key")
    private Integer createdDateKey;

    @Column(name = "room_type_key")
    private Integer roomTypeKey;

    @Column(name = "payment_method_key")
    private Integer paymentMethodKey;

    @Column(name = "adults_count")
    private Integer adultsCount;

    @Column(name = "children_count")
    private Integer childrenCount;

    @Column(name = "nights_count")
    private Integer nightsCount;

    @Column(name = "room_amount")
    private BigDecimal roomAmount;

    @Column(name = "total_service_amount")
    private BigDecimal totalServiceAmount;

    @Column(name = "total_payment_amount")
    private BigDecimal totalPaymentAmount;

    @Column(name = "has_review")
    private Integer hasReview;

    @Column(name = "review_rating")
    private Integer reviewRating;

    @Column(name = "has_comment")
    private Integer hasComment;
}
