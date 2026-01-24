package com.example.Hotels_DWBI.repository;

import com.example.Hotels_DWBI.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
