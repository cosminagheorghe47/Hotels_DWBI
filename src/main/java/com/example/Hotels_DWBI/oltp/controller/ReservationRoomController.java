package com.example.Hotels_DWBI.oltp.controller;

import com.example.Hotels_DWBI.oltp.model.ReservationRoom;
import com.example.Hotels_DWBI.oltp.service.ReservationRoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation-rooms")
public class ReservationRoomController {

    private final ReservationRoomService service;

    public ReservationRoomController(ReservationRoomService service) {
        this.service = service;
    }

    @GetMapping
    public List<ReservationRoom> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ReservationRoom getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ReservationRoom create(@RequestBody ReservationRoom rr) {
        return service.save(rr);
    }

    @PutMapping("/{id}")
    public ReservationRoom update(@PathVariable Integer id, @RequestBody ReservationRoom rr) {
        return service.update(id, rr);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
