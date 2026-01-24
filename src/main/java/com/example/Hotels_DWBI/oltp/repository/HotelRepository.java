package com.example.Hotels_DWBI.oltp.repository;

import com.example.Hotels_DWBI.oltp.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
