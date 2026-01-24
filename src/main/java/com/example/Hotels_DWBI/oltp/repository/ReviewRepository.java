package com.example.Hotels_DWBI.oltp.repository;

import com.example.Hotels_DWBI.oltp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Review> findByReservationReservationId(Integer reservationId);
}
