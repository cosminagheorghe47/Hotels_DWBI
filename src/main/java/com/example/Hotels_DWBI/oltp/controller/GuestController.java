package com.example.Hotels_DWBI.oltp.controller;

import com.example.Hotels_DWBI.oltp.model.Guest;
import com.example.Hotels_DWBI.oltp.service.GuestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public List<Guest> getAllGuests() {
        return guestService.getAllGuests();
    }

    @GetMapping("/{id}")
    public Guest getGuestById(@PathVariable Integer id) {
        return guestService.getGuestById(id);
    }

    @PostMapping
    public Guest createGuest(@RequestBody Guest guest) {
        return guestService.createGuest(guest);
    }

    @PutMapping("/{id}")
    public Guest updateGuest(
            @PathVariable Integer id,
            @RequestBody Guest guest
    ) {
        return guestService.updateGuest(id, guest);
    }

    @DeleteMapping("/{id}")
    public void deleteGuest(@PathVariable Integer id) {
        guestService.deleteGuest(id);
    }
}
