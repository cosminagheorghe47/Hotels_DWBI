package com.example.Hotels_DWBI.dw.repository;


import com.example.Hotels_DWBI.dw.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactReservationSummaryRepository extends JpaRepository<FactReservationSummary, Integer> {
    FactReservationSummary findByReservationIdOltp(Integer reservationIdOltp);
}


