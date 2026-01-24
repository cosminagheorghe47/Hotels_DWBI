package com.example.Hotels_DWBI.oltp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "ROOM_TYPES")
@Data
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_type_id", nullable = false)
    private Integer roomTypeId;

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
