package com.example.Hotels_DWBI.dw.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ReservationValidationDto {

    @Data
    public static class OltpSide {
        private Integer reservationId;
        private Integer guestId;
        private Integer hotelId;
        private String bookingChannel;
        private String status;
        private Integer adultsCount;
        private Integer childrenCount;
        private Integer nightsCount;
        private BigDecimal totalPaymentAmount;
        private boolean hasReview;

        public Integer getReservationId() {
            return reservationId;
        }

        public void setReservationId(Integer reservationId) {
            this.reservationId = reservationId;
        }

        public Integer getGuestId() {
            return guestId;
        }

        public void setGuestId(Integer guestId) {
            this.guestId = guestId;
        }

        public Integer getHotelId() {
            return hotelId;
        }

        public void setHotelId(Integer hotelId) {
            this.hotelId = hotelId;
        }

        public String getBookingChannel() {
            return bookingChannel;
        }

        public void setBookingChannel(String bookingChannel) {
            this.bookingChannel = bookingChannel;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public BigDecimal getTotalPaymentAmount() {
            return totalPaymentAmount;
        }

        public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
            this.totalPaymentAmount = totalPaymentAmount;
        }

        public boolean isHasReview() {
            return hasReview;
        }

        public void setHasReview(boolean hasReview) {
            this.hasReview = hasReview;
        }
    }



    @Data
    public static class DwFactRow {
        private Integer reservationKey;
        private Integer reservationRoomIdOltp;
        private Integer hotelKey;
        private Integer guestKey;
        private Integer channelKey;
        private Integer statusKey;
        private Integer roomTypeKey;
        private Integer paymentMethodKey;
        private Integer checkInDateKey;
        private Integer checkOutDateKey;
        private Integer createdDateKey;
        private Integer adultsCount;
        private Integer childrenCount;
        private Integer nightsCount;
        private BigDecimal roomAmount;
        private BigDecimal totalPaymentAmount;
        private Integer hasReview;
        private Integer reviewRating;
        private Integer hasComment;

        public Integer getReservationKey() {
            return reservationKey;
        }

        public void setReservationKey(Integer reservationKey) {
            this.reservationKey = reservationKey;
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

    private OltpSide oltp;
    private List<DwFactRow> dwFacts;

    public OltpSide getOltp() {
        return oltp;
    }

    public void setOltp(OltpSide oltp) {
        this.oltp = oltp;
    }

    public List<DwFactRow> getDwFacts() {
        return dwFacts;
    }

    public void setDwFacts(List<DwFactRow> dwFacts) {
        this.dwFacts = dwFacts;
    }
}
