package com.example.Hotels_DWBI.dw.repository;

import com.example.Hotels_DWBI.dw.model.DimDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimDateRepository extends JpaRepository<DimDate, Integer> {
    DimDate findByFullDate(java.time.LocalDate date);
}
