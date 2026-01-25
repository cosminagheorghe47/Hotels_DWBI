package com.example.Hotels_DWBI.dw.service;

import com.example.Hotels_DWBI.dw.model.*;
import com.example.Hotels_DWBI.dw.repository.*;
import com.example.Hotels_DWBI.oltp.model.*;
import com.example.Hotels_DWBI.oltp.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class DwPropagationService {

    private final DimGuestRepository dimGuestRepo;
    private final DimHotelRepository dimHotelRepo;
    private final DimRoomTypeRepository dimRoomTypeRepo;
    private final DimBookingChannelRepository dimBookingChannelRepo;
    private final DimPaymentMethodRepository dimPaymentMethodRepo;
    private final DimReservationStatusRepository dimReservationStatusRepo;
    private final DimDateRepository dimDateRepo;
    private final FactReservationSummaryRepository factReservationRepo;

    private final GuestRepository guestRepo;
    private final HotelRepository hotelRepo;
    private final RoomTypeRepository roomTypeRepo;
    private final ReservationRepository reservationRepo;
    private final ReservationRoomRepository reservationRoomRepo;
    private final PaymentRepository paymentRepo;
    private final ReviewRepository reviewRepo;

    public DwPropagationService(
            DimGuestRepository dimGuestRepo,
            DimHotelRepository dimHotelRepo,
            DimRoomTypeRepository dimRoomTypeRepo,
            DimBookingChannelRepository dimBookingChannelRepo,
            DimPaymentMethodRepository dimPaymentMethodRepo,
            DimReservationStatusRepository dimReservationStatusRepo,
            DimDateRepository dimDateRepo,
            FactReservationSummaryRepository factReservationRepo,
            GuestRepository guestRepo,
            HotelRepository hotelRepo,
            RoomTypeRepository roomTypeRepo,
            ReservationRepository reservationRepo,
            ReservationRoomRepository reservationRoomRepo,
            PaymentRepository paymentRepo,
            ReviewRepository reviewRepo
    ) {
        this.dimGuestRepo = dimGuestRepo;
        this.dimHotelRepo = dimHotelRepo;
        this.dimRoomTypeRepo = dimRoomTypeRepo;
        this.dimBookingChannelRepo = dimBookingChannelRepo;
        this.dimPaymentMethodRepo = dimPaymentMethodRepo;
        this.dimReservationStatusRepo = dimReservationStatusRepo;
        this.dimDateRepo = dimDateRepo;
        this.factReservationRepo = factReservationRepo;
        this.guestRepo = guestRepo;
        this.hotelRepo = hotelRepo;
        this.roomTypeRepo = roomTypeRepo;
        this.reservationRepo = reservationRepo;
        this.reservationRoomRepo = reservationRoomRepo;
        this.paymentRepo = paymentRepo;
        this.reviewRepo = reviewRepo;
    }

    @Transactional("dwTransactionManager")
    public void propagateGuest(Integer guestId) {
        Guest guest = guestRepo.findById(guestId).orElseThrow();
        DimGuest dimGuest = dimGuestRepo.findByGuestIdOltp(guest.getGuestId());
        if (dimGuest == null) {
            dimGuest = new DimGuest();
            dimGuest.setGuestIdOltp(guest.getGuestId());
        }
        dimGuest.setFirstName(guest.getFirstName());
        dimGuest.setLastName(guest.getLastName());
        dimGuest.setEmail(guest.getEmail());
        dimGuest.setNationality(guest.getNationality());
        dimGuest.setBirthDate(guest.getBirthDate());
        dimGuestRepo.save(dimGuest);
    }

    @Transactional("dwTransactionManager")
    public void propagateHotel(Integer hotelId) {
        Hotel hotel = hotelRepo.findById(hotelId).orElseThrow();
        DimHotel dimHotel = dimHotelRepo.findByHotelIdOltp(hotel.getHotelId());
        if (dimHotel == null) {
            dimHotel = new DimHotel();
            dimHotel.setHotelIdOltp(hotel.getHotelId());
        }
        dimHotel.setName(hotel.getName());
        dimHotel.setStars(hotel.getStars());
        dimHotel.setCountry(hotel.getCountry());
        dimHotel.setCity(hotel.getCity());
        dimHotel.setAddress(hotel.getAddress());
        dimHotelRepo.save(dimHotel);
    }

    @Transactional("dwTransactionManager")
    public void propagateRoomType(Integer roomTypeId) {
        RoomType roomType = roomTypeRepo.findById(roomTypeId).orElseThrow();
        DimRoomType dimRoomType = dimRoomTypeRepo.findByRoomTypeIdOltp(roomType.getRoomTypeId());
        if (dimRoomType == null) {
            dimRoomType = new DimRoomType();
            dimRoomType.setRoomTypeIdOltp(roomType.getRoomTypeId());
        }
        dimRoomType.setName(roomType.getName());
        dimRoomType.setMaxAdults(roomType.getMaxAdults());
        dimRoomType.setMaxChildren(roomType.getMaxChildren());
        dimRoomType.setBasePricePerNight(roomType.getBasePricePerNight());
        dimRoomType.setCurrency(roomType.getCurrency());
        dimRoomTypeRepo.save(dimRoomType);
    }

    @Transactional("dwTransactionManager")
    public void propagateReservation(Integer reservationId) {
        Reservation res = reservationRepo.findById(reservationId).orElseThrow();

        // --- Guest și Hotel ---
        propagateGuest(res.getGuest().getGuestId());
        propagateHotel(res.getHotel().getHotelId());

        DimGuest guestDw = dimGuestRepo.findByGuestIdOltp(res.getGuest().getGuestId());
        DimHotel hotelDw = dimHotelRepo.findByHotelIdOltp(res.getHotel().getHotelId());

        // --- Booking Channel ---
        DimBookingChannel channelDw = dimBookingChannelRepo.findByChannelName(res.getBookingChannel().name());
        if (channelDw == null) {
            channelDw = new DimBookingChannel();
            channelDw.setChannelName(res.getBookingChannel().name());
            dimBookingChannelRepo.save(channelDw);
        }

        // --- Reservation Status ---
        DimReservationStatus statusDw = dimReservationStatusRepo.findByStatusName(res.getStatus().name());
        if (statusDw == null) {
            statusDw = new DimReservationStatus();
            statusDw.setStatusName(res.getStatus().name());
            dimReservationStatusRepo.save(statusDw);
        }

        // --- Date dim ---
        DimDate checkInDate = getOrCreateDate(res.getCheckInDate());
        DimDate checkOutDate = getOrCreateDate(res.getCheckOutDate());
        long nights = ChronoUnit.DAYS.between(res.getCheckInDate(), res.getCheckOutDate());

        // --- Payment (unul singur) ---
        Payment payment = paymentRepo.findByReservationReservationId(res.getReservationId())
                .orElseThrow();
        BigDecimal paymentAmount = payment.getAmount();

        // --- Review (unul singur) ---
        Review review = reviewRepo.findByReservationReservationId(res.getReservationId()).orElse(null);
        boolean hasReview = review != null;
        Integer reviewRating = hasReview ? review.getRating() : null;
        boolean hasComment = hasReview && review.getCommentReview() != null && !review.getCommentReview().isEmpty();

        // --- Pentru fiecare cameră creez un rând fact ---
        List<ReservationRoom> resRooms = reservationRoomRepo.findByReservationReservationId(res.getReservationId());
        for (ReservationRoom resRoom : resRooms) {
            // Propagare RoomType
            propagateRoomType(resRoom.getRoom().getRoomType().getRoomTypeId());
            DimRoomType roomTypeDw = dimRoomTypeRepo.findByRoomTypeIdOltp(resRoom.getRoom().getRoomType().getRoomTypeId());

            BigDecimal roomAmount = resRoom.getFinalPricePerNight().multiply(BigDecimal.valueOf(nights));

            FactReservationSummary fact = new FactReservationSummary();
            fact.setReservationIdOltp(res.getReservationId());
            fact.setHotelKey(hotelDw.getHotelKey());
            fact.setGuestKey(guestDw.getGuestKey());
            fact.setChannelKey(channelDw.getChannelKey());
            fact.setStatusKey(statusDw.getStatusKey());
            fact.setCheckInDateKey(checkInDate.getDateKey());
            fact.setCheckOutDateKey(checkOutDate.getDateKey());
            fact.setCreatedDateKey(checkInDate.getDateKey());
            fact.setRoomTypeKey(roomTypeDw.getRoomTypeKey());
            fact.setRoomAmount(roomAmount);
            fact.setTotalPaymentAmount(paymentAmount);
            fact.setAdultsCount(res.getAdultsCount());
            fact.setChildrenCount(res.getChildrenCount());
            fact.setNightsCount((int) nights);
            fact.setHasReview(hasReview ? 1 : 0);
            fact.setReviewRating(reviewRating);
            fact.setHasComment(hasComment ? 1 : 0);

            factReservationRepo.save(fact);
        }
    }


    private DimDate getOrCreateDate(java.time.LocalDate date) {
        DimDate dimDate = dimDateRepo.findByFullDate(date);
        if (dimDate == null) {
            dimDate = new DimDate();
            dimDate.setFullDate(date);
            dimDate.setDayNo(date.getDayOfMonth());
            dimDate.setMonthNo(date.getMonthValue());
            dimDate.setMonthName(date.getMonth().name());
            dimDate.setQuarterNo((date.getMonthValue() - 1) / 3 + 1);
            dimDate.setYearNo(date.getYear());
            dimDate.setIsWeekend(date.getDayOfWeek().getValue() >= 6 ? 1 : 0);
            dimDateRepo.save(dimDate);
        }
        return dimDate;
    }
}
