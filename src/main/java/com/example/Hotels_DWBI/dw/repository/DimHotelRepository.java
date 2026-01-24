package com.example.Hotels_DWBI.dw.repository;

import com.example.Hotels_DWBI.dw.model.DimHotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimHotelRepository extends JpaRepository<DimHotel, Integer> {
    DimHotel findByHotelIdOltp(Integer hotelIdOltp);
}

