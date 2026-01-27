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
    }

    private OltpSide oltp;
    private List<DwFactRow> dwFacts;
}
