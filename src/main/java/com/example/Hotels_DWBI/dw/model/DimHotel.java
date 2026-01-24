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

    @Column(name = "hotel_id_oltp")
    private Integer hotelIdOltp;

    @Column(name = "name")
    private String name;

    @Column(name = "stars")
    private Integer stars;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;
}
