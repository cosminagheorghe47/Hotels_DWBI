package com.example.Hotels_DWBI.oltp.repository;

import com.example.Hotels_DWBI.oltp.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {

    @Query("SELECT c FROM City c LEFT JOIN FETCH c.country ORDER BY c.cityName ASC")
    List<City> findAllWithCountryOrderByCityNameAsc();

    @Query("SELECT c FROM City c LEFT JOIN FETCH c.country WHERE c.cityId = :id")
    Optional<City> findDetailById(@Param("id") Integer id);
}
