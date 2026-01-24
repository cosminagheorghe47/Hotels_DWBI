package com.example.Hotels_DWBI.service;

import com.example.Hotels_DWBI.model.Guest;
import com.example.Hotels_DWBI.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    public Guest getGuestById(Integer id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id " + id));
    }

    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    public Guest updateGuest(Integer id, Guest updatedGuest) {
        Guest existing = getGuestById(id);

        existing.setFirstName(updatedGuest.getFirstName());
        existing.setLastName(updatedGuest.getLastName());
        existing.setEmail(updatedGuest.getEmail());
        existing.setPhone(updatedGuest.getPhone());
        existing.setNationality(updatedGuest.getNationality());
        existing.setBirthDate(updatedGuest.getBirthDate());

        return guestRepository.save(existing);
    }

    public void deleteGuest(Integer id) {
        guestRepository.deleteById(id);
    }
}
