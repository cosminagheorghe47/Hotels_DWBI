package com.example.Hotels_DWBI.dw.dto;

import lombok.Data;

@Data
public class HotelValidationDto {

    @Data
    public static class OltpSide {
        private Integer hotelId;
        private String name;
        private Integer stars;
        private String country;
        private String city;
        private String address;
    }

    @Data
    public static class DwSide {
        private Integer hotelKey;
        private Integer hotelIdOltp;
        private String name;
        private Integer stars;
        private String country;
        private String city;
        private String address;
    }

    private OltpSide oltp;
    private DwSide dw;
}
