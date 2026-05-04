package com.example.Hotels_DWBI.oltp.repository;

import com.example.Hotels_DWBI.oltp.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    @Query("SELECT DISTINCT h FROM Hotel h LEFT JOIN FETCH h.city c LEFT JOIN FETCH c.country")
    List<Hotel> findAllWithCityCountry();

    @Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.city c LEFT JOIN FETCH c.country WHERE h.hotelId = :id")
    Optional<Hotel> findDetailById(@Param("id") Integer id);
}
