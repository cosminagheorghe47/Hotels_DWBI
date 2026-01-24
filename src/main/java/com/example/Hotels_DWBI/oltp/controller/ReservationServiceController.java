package com.example.Hotels_DWBI.oltp.controller;

import com.example.Hotels_DWBI.oltp.model.ReservationService;
import com.example.Hotels_DWBI.oltp.service.ReservationServiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation-services")
public class ReservationServiceController {

    private final ReservationServiceService service;

    public ReservationServiceController(ReservationServiceService service) {
        this.service = service;
    }

    @GetMapping
    public List<ReservationService> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ReservationService getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ReservationService create(@RequestBody ReservationService rs) {
        return service.save(rs);
    }

    @PutMapping("/{id}")
    public ReservationService update(@PathVariable Integer id, @RequestBody ReservationService rs) {
        return service.update(id, rs);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
