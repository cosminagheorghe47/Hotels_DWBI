package com.example.Hotels_DWBI.dw.repository;

import com.example.Hotels_DWBI.dw.model.DimGuest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimGuestRepository extends JpaRepository<DimGuest, Integer> {
    DimGuest findByGuestIdOltp(Integer guestIdOltp);
}

