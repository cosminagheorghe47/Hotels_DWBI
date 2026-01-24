package com.example.Hotels_DWBI.oltp.repository;

import com.example.Hotels_DWBI.oltp.model.ReservationService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationServiceRepository extends JpaRepository<ReservationService, Integer> {
}
