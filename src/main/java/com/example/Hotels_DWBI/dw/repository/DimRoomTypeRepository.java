package com.example.Hotels_DWBI.dw.repository;

import com.example.Hotels_DWBI.dw.model.DimRoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimRoomTypeRepository extends JpaRepository<DimRoomType, Integer> {
    DimRoomType findByRoomTypeIdOltp(Integer roomTypeIdOltp);
}
