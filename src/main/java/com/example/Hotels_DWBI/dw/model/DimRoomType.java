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

    @Column(name = "room_type_id_oltp")
    private Integer roomTypeIdOltp;

    @Column(name = "name")
    private String name;

    @Column(name = "max_adults")
    private Integer maxAdults;

    @Column(name = "max_children")
    private Integer maxChildren;

    @Column(name = "base_price_per_night")
    private BigDecimal basePricePerNight;

    @Column(name = "currency")
    private String currency;
}
