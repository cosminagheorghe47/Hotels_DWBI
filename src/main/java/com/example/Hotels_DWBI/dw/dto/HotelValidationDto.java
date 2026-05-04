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

        public Integer getHotelId() {
            return hotelId;
        }

        public void setHotelId(Integer hotelId) {
            this.hotelId = hotelId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getStars() {
            return stars;
        }

        public void setStars(Integer stars) {
            this.stars = stars;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
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

        public Integer getHotelKey() {
            return hotelKey;
        }

        public void setHotelKey(Integer hotelKey) {
            this.hotelKey = hotelKey;
        }

        public Integer getHotelIdOltp() {
            return hotelIdOltp;
        }

        public void setHotelIdOltp(Integer hotelIdOltp) {
            this.hotelIdOltp = hotelIdOltp;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getStars() {
            return stars;
        }

        public void setStars(Integer stars) {
            this.stars = stars;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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
