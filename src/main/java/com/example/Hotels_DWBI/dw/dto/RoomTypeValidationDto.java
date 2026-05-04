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

        public Integer getRoomTypeId() {
            return roomTypeId;
        }

        public void setRoomTypeId(Integer roomTypeId) {
            this.roomTypeId = roomTypeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getMaxAdults() {
            return maxAdults;
        }

        public void setMaxAdults(Integer maxAdults) {
            this.maxAdults = maxAdults;
        }

        public Integer getMaxChildren() {
            return maxChildren;
        }

        public void setMaxChildren(Integer maxChildren) {
            this.maxChildren = maxChildren;
        }

        public BigDecimal getBasePricePerNight() {
            return basePricePerNight;
        }

        public void setBasePricePerNight(BigDecimal basePricePerNight) {
            this.basePricePerNight = basePricePerNight;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
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

        public Integer getRoomTypeKey() {
            return roomTypeKey;
        }

        public void setRoomTypeKey(Integer roomTypeKey) {
            this.roomTypeKey = roomTypeKey;
        }

        public Integer getRoomTypeIdOltp() {
            return roomTypeIdOltp;
        }

        public void setRoomTypeIdOltp(Integer roomTypeIdOltp) {
            this.roomTypeIdOltp = roomTypeIdOltp;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getMaxAdults() {
            return maxAdults;
        }

        public void setMaxAdults(Integer maxAdults) {
            this.maxAdults = maxAdults;
        }

        public Integer getMaxChildren() {
            return maxChildren;
        }

        public void setMaxChildren(Integer maxChildren) {
            this.maxChildren = maxChildren;
        }

        public BigDecimal getBasePricePerNight() {
            return basePricePerNight;
        }

        public void setBasePricePerNight(BigDecimal basePricePerNight) {
            this.basePricePerNight = basePricePerNight;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
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
