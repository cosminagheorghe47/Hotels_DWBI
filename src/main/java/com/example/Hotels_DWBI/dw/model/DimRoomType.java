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
}
