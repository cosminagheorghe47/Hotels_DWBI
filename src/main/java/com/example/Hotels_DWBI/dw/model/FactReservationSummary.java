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

    @Column(name = "reservation_id_oltp", nullable = false)
    private Integer reservationIdOltp;

    @Column(name = "reservation_room_id_oltp", nullable = false)
    private Integer reservationRoomIdOltp;

    @Column(name = "hotel_key", nullable = false)
    private Integer hotelKey;

    @Column(name = "guest_key", nullable = false)
    private Integer guestKey;

    @Column(name = "channel_key", nullable = false)
    private Integer channelKey;

    @Column(name = "status_key", nullable = false)
    private Integer statusKey;

    @Column(name = "check_in_date_key", nullable = false)
    private Integer checkInDateKey;

    @Column(name = "check_out_date_key", nullable = false)
    private Integer checkOutDateKey;

    @Column(name = "created_date_key", nullable = false)
    private Integer createdDateKey;

    @Column(name = "room_type_key", nullable = false)
    private Integer roomTypeKey;

    @Column(name = "payment_method_key")
    private Integer paymentMethodKey;

    @Column(name = "adults_count", nullable = false)
    private Integer adultsCount;

    @Column(name = "children_count", nullable = false)
    private Integer childrenCount;

    @Column(name = "nights_count", nullable = false)
    private Integer nightsCount;

    @Column(name = "room_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal roomAmount;

    @Column(name = "total_service_amount", precision = 12, scale = 2)
    private BigDecimal totalServiceAmount;

    @Column(name = "total_payment_amount", precision = 12, scale = 2)
    private BigDecimal totalPaymentAmount;

    @Column(name = "has_review", nullable = false)
    private Integer hasReview;

    @Column(name = "review_rating")
    private Integer reviewRating;

    @Column(name = "has_comment", nullable = false)
    private Integer hasComment;
}
