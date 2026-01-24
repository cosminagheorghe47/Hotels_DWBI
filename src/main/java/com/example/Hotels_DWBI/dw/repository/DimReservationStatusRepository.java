package com.example.Hotels_DWBI.dw.repository;

import com.example.Hotels_DWBI.dw.model.DimReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimReservationStatusRepository extends JpaRepository<DimReservationStatus, Integer> {
    DimReservationStatus findByStatusName(String statusName);
}
