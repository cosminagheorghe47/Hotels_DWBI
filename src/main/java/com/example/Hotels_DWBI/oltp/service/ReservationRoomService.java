package com.example.Hotels_DWBI.oltp.service;

import com.example.Hotels_DWBI.oltp.model.ReservationRoom;
import com.example.Hotels_DWBI.oltp.repository.ReservationRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationRoomService {

    private final ReservationRoomRepository repository;

    public ReservationRoomService(ReservationRoomRepository repository) {
        this.repository = repository;
    }

    public List<ReservationRoom> findAll() {
        return repository.findAll();
    }

    public ReservationRoom findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ReservationRoom not found with id " + id));
    }

    public ReservationRoom save(ReservationRoom rr) {
        return repository.save(rr);
    }

    public ReservationRoom update(Integer id, ReservationRoom updated) {
        ReservationRoom existing = findById(id);

        existing.setReservation(updated.getReservation());
        existing.setRoom(updated.getRoom());
        existing.setPricePerNight(updated.getPricePerNight());
        existing.setDiscountAmount(updated.getDiscountAmount());
        existing.setFinalPricePerNight(updated.getFinalPricePerNight());

        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
