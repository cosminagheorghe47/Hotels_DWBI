package com.example.Hotels_DWBI.service;

import com.example.Hotels_DWBI.model.ReservationService;
import com.example.Hotels_DWBI.repository.ReservationServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceService {

    private final ReservationServiceRepository repository;

    public ReservationServiceService(ReservationServiceRepository repository) {
        this.repository = repository;
    }

    public List<ReservationService> findAll() {
        return repository.findAll();
    }

    public ReservationService findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ReservationService not found with id " + id));
    }

    public ReservationService save(ReservationService rs) {
        return repository.save(rs);
    }

    public ReservationService update(Integer id, ReservationService updated) {
        ReservationService existing = findById(id);

        existing.setReservation(updated.getReservation());
        existing.setService(updated.getService());
        existing.setQuantity(updated.getQuantity());
        existing.setUnitPriceAtBooking(updated.getUnitPriceAtBooking());
        existing.setLineTotal(updated.getLineTotal());

        return repository.save(existing);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
