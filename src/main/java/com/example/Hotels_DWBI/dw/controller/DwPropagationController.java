package com.example.Hotels_DWBI.dw.controller;

import com.example.Hotels_DWBI.dw.dto.GuestValidationDto;
import com.example.Hotels_DWBI.dw.dto.HotelValidationDto;
import com.example.Hotels_DWBI.dw.dto.ReservationValidationDto;
import com.example.Hotels_DWBI.dw.dto.RoomTypeValidationDto;
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
    @GetMapping("/validate/reservation/{id}")
    public ReservationValidationDto validateReservation(@PathVariable Integer id)
    { return dwService.validateReservationPropagation(id); }
    @GetMapping("/validate/hotel/{id}")
    public HotelValidationDto validateHotel(@PathVariable Integer id) {
        return dwService.validateHotel(id);
    }
    @GetMapping("/validate/roomtype/{id}")
    public RoomTypeValidationDto validateRoomType(@PathVariable Integer id) {
        return dwService.validateRoomType(id);
    }
    @GetMapping("/validate/guest/{id}")
    public GuestValidationDto validateGuest(@PathVariable Integer id) {
        return dwService.validateGuest(id);
    }


}
