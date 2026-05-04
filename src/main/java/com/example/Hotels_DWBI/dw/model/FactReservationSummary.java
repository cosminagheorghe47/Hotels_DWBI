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

    public Integer getReservationKey() {
        return reservationKey;
    }

    public void setReservationKey(Integer reservationKey) {
        this.reservationKey = reservationKey;
    }

    public Integer getReservationIdOltp() {
        return reservationIdOltp;
    }

    public void setReservationIdOltp(Integer reservationIdOltp) {
        this.reservationIdOltp = reservationIdOltp;
    }

    public Integer getReservationRoomIdOltp() {
        return reservationRoomIdOltp;
    }

    public void setReservationRoomIdOltp(Integer reservationRoomIdOltp) {
        this.reservationRoomIdOltp = reservationRoomIdOltp;
    }

    public Integer getHotelKey() {
        return hotelKey;
    }

    public void setHotelKey(Integer hotelKey) {
        this.hotelKey = hotelKey;
    }

    public Integer getGuestKey() {
        return guestKey;
    }

    public void setGuestKey(Integer guestKey) {
        this.guestKey = guestKey;
    }

    public Integer getChannelKey() {
        return channelKey;
    }

    public void setChannelKey(Integer channelKey) {
        this.channelKey = channelKey;
    }

    public Integer getStatusKey() {
        return statusKey;
    }

    public void setStatusKey(Integer statusKey) {
        this.statusKey = statusKey;
    }

    public Integer getCheckInDateKey() {
        return checkInDateKey;
    }

    public void setCheckInDateKey(Integer checkInDateKey) {
        this.checkInDateKey = checkInDateKey;
    }

    public Integer getCheckOutDateKey() {
        return checkOutDateKey;
    }

    public void setCheckOutDateKey(Integer checkOutDateKey) {
        this.checkOutDateKey = checkOutDateKey;
    }

    public Integer getCreatedDateKey() {
        return createdDateKey;
    }

    public void setCreatedDateKey(Integer createdDateKey) {
        this.createdDateKey = createdDateKey;
    }

    public Integer getRoomTypeKey() {
        return roomTypeKey;
    }

    public void setRoomTypeKey(Integer roomTypeKey) {
        this.roomTypeKey = roomTypeKey;
    }

    public Integer getPaymentMethodKey() {
        return paymentMethodKey;
    }

    public void setPaymentMethodKey(Integer paymentMethodKey) {
        this.paymentMethodKey = paymentMethodKey;
    }

    public Integer getAdultsCount() {
        return adultsCount;
    }

    public void setAdultsCount(Integer adultsCount) {
        this.adultsCount = adultsCount;
    }

    public Integer getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(Integer childrenCount) {
        this.childrenCount = childrenCount;
    }

    public Integer getNightsCount() {
        return nightsCount;
    }

    public void setNightsCount(Integer nightsCount) {
        this.nightsCount = nightsCount;
    }

    public BigDecimal getRoomAmount() {
        return roomAmount;
    }

    public void setRoomAmount(BigDecimal roomAmount) {
        this.roomAmount = roomAmount;
    }

    public BigDecimal getTotalServiceAmount() {
        return totalServiceAmount;
    }

    public void setTotalServiceAmount(BigDecimal totalServiceAmount) {
        this.totalServiceAmount = totalServiceAmount;
    }

    public BigDecimal getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
        this.totalPaymentAmount = totalPaymentAmount;
    }

    public Integer getHasReview() {
        return hasReview;
    }

    public void setHasReview(Integer hasReview) {
        this.hasReview = hasReview;
    }

    public Integer getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(Integer reviewRating) {
        this.reviewRating = reviewRating;
    }

    public Integer getHasComment() {
        return hasComment;
    }

    public void setHasComment(Integer hasComment) {
        this.hasComment = hasComment;
    }
}
