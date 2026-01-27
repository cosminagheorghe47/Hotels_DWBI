package com.example.Hotels_DWBI.dw.repository;


import com.example.Hotels_DWBI.dw.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FactReservationSummaryRepository extends JpaRepository<FactReservationSummary, Integer> {
    //FactReservationSummary findByReservationIdOltp(Integer reservationIdOltp);
    List<FactReservationSummary> findByReservationIdOltp(Integer reservationId);
}


