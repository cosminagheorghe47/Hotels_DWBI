package com.example.Hotels_DWBI.dw.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "DIM_ROOM_TYPE")
@Data
public class DimRoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_key", nullable = false)
    private Integer roomTypeKey;

    @Column(name = "room_type_id_oltp", nullable = false)
    private Integer roomTypeIdOltp;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "max_adults", nullable = false)
    private Integer maxAdults;

    @Column(name = "max_children", nullable = false)
    private Integer maxChildren;

    @Column(name = "base_price_per_night", nullable = false)
    private BigDecimal basePricePerNight;

    @Column(name = "currency", nullable = false)
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
