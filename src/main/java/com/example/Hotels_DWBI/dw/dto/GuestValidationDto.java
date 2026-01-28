package com.example.Hotels_DWBI.dw.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GuestValidationDto {

    @Data
    public static class OltpSide {
        private Integer guestId;
        private String firstName;
        private String lastName;
        private String nationality;
        private LocalDate birthDate;
        private String email;
    }

    @Data
    public static class DwSide {
        private Integer guestKey;
        private Integer guestIdOltp;
        private String firstName;
        private String lastName;
        private String nationality;
        private LocalDate birthDate;
        private String email;
    }

    private OltpSide oltp;
    private DwSide dw;
}

