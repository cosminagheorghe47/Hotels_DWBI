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

        public Integer getGuestId() {
            return guestId;
        }

        public void setGuestId(Integer guestId) {
            this.guestId = guestId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
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

        public Integer getGuestKey() {
            return guestKey;
        }

        public void setGuestKey(Integer guestKey) {
            this.guestKey = guestKey;
        }

        public Integer getGuestIdOltp() {
            return guestIdOltp;
        }

        public void setGuestIdOltp(Integer guestIdOltp) {
            this.guestIdOltp = guestIdOltp;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    private OltpSide oltp;
    private DwSide dw;

    public OltpSide getOltp() {
        return oltp;
    }

    public void setOltp(OltpSide oltp) {
        this.oltp = oltp;
    }

    public DwSide getDw() {
        return dw;
    }

    public void setDw(DwSide dw) {
        this.dw = dw;
    }
}

