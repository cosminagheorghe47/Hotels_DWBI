package com.example.Hotels_DWBI.dw.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomTypeValidationDto {

    @Data
    public static class OltpSide {
        private Integer roomTypeId;
        private String name;
        private Integer maxAdults;
        private Integer maxChildren;
        private BigDecimal basePricePerNight;
        private String currency;
    }

    @Data
    public static class DwSide {
        private Integer roomTypeKey;
        private Integer roomTypeIdOltp;
        private String name;
        private Integer maxAdults;
        private Integer maxChildren;
        private BigDecimal basePricePerNight;
        private String currency;
    }

    private OltpSide oltp;
    private DwSide dw;
}
