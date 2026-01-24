package com.example.Hotels_DWBI.dw.controller;

import com.example.Hotels_DWBI.dw.service.DwPropagationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dw/propagate")
public class DwPropagationController {

    private final DwPropagationService dwService;

    public DwPropagationController(DwPropagationService dwService) {
        this.dwService = dwService;
    }

    @PostMapping("/guest/{id}")
    public ResponseEntity<Void> propagateGuest(@PathVariable Integer id) {
        dwService.propagateGuest(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/hotel/{id}")
    public ResponseEntity<Void> propagateHotel(@PathVariable Integer id) {
        dwService.propagateHotel(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/roomtype/{id}")
    public ResponseEntity<Void> propagateRoomType(@PathVariable Integer id) {
        dwService.propagateRoomType(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reservation/{reservationId}")
    public String propagateReservation(@PathVariable Integer reservationId) {
        dwService.propagateReservation(reservationId);
        return "Reservation " + reservationId + " propagated successfully!";
    }
}
