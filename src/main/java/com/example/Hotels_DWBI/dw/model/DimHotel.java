package com.example.Hotels_DWBI.dw.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DIM_HOTEL")
@Data
public class DimHotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_key", nullable = false)
    private Integer hotelKey;

    @Column(name = "hotel_id_oltp", nullable = false)
    private Integer hotelIdOltp;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "stars", nullable = false)
    private Integer stars;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "address")
    private String address;
}
