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
