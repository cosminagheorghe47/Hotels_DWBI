package com.example.Hotels_DWBI.oltp.controller;

import com.example.Hotels_DWBI.oltp.model.City;
import com.example.Hotels_DWBI.oltp.repository.CityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping
    public List<City> listAll() {
        return cityRepository.findAllWithCountryOrderByCityNameAsc();
    }

    @GetMapping("/{id}")
    public City getById(@PathVariable Integer id) {
        return cityRepository.findDetailById(id)
                .orElseThrow(() -> new IllegalArgumentException("City not found: " + id));
    }
}
