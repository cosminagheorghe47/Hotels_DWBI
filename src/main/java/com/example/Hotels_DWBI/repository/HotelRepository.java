package com.example.Hotels_DWBI.repository;

import com.example.Hotels_DWBI.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
