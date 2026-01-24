package com.example.Hotels_DWBI.service;

import com.example.Hotels_DWBI.model.Reservation;
import com.example.Hotels_DWBI.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id " + id));
    }

    public Reservation createReservation(Reservation reservation) {
        reservation.setCreatedAt(LocalDateTime.now());
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Integer id, Reservation updatedReservation) {
        Reservation reservation = getReservationById(id);

        reservation.setGuest(updatedReservation.getGuest());
        reservation.setHotel(updatedReservation.getHotel());
        reservation.setCheckInDate(updatedReservation.getCheckInDate());
        reservation.setCheckOutDate(updatedReservation.getCheckOutDate());
        reservation.setAdultsCount(updatedReservation.getAdultsCount());
        reservation.setChildrenCount(updatedReservation.getChildrenCount());
        reservation.setBookingChannel(updatedReservation.getBookingChannel());
        reservation.setStatus(updatedReservation.getStatus());
        reservation.setCancelledAt(updatedReservation.getCancelledAt());
        reservation.setNotes(updatedReservation.getNotes());

        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Integer id) {
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
    }
}
