package com.example.Hotels_DWBI.dw.service;

import com.example.Hotels_DWBI.dw.dto.ReservationValidationDto;
import com.example.Hotels_DWBI.dw.model.*;
import com.example.Hotels_DWBI.dw.repository.*;
import com.example.Hotels_DWBI.oltp.model.*;
import com.example.Hotels_DWBI.oltp.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

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

        Reservation res = reservationRepo.findById(reservationId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Reservation not found: " + reservationId)
                );

        DimGuest guestDw = dimGuestRepo.findByGuestIdOltp(res.getGuest().getGuestId());
        DimHotel hotelDw = dimHotelRepo.findByHotelIdOltp(res.getHotel().getHotelId());
        DimBookingChannel channelDw = dimBookingChannelRepo.findByChannelName(res.getBookingChannel().name());
        DimReservationStatus statusDw = dimReservationStatusRepo.findByStatusName(res.getStatus().name());

        DimDate checkInDate  = getDate(res.getCheckInDate());
        DimDate checkOutDate = getDate(res.getCheckOutDate());
        DimDate createdDate  = getDate(res.getCreatedAt().toLocalDate());

        int nights = (int) ChronoUnit.DAYS.between(res.getCheckInDate(), res.getCheckOutDate());

        // ===== PAYMENT (SAFE) =====

        Payment payment = paymentRepo
                .findByReservationReservationId(res.getReservationId())
                .orElse(null);

        BigDecimal totalPaymentAmount = null;
        Integer paymentMethodKey = null;

        if (payment != null) {
            totalPaymentAmount = payment.getAmount();

            if (payment.getMethod() != null) {
                String methodName = payment.getMethod().name().trim();
                DimPaymentMethod paymentMethodDw =
                        dimPaymentMethodRepo.findByMethodName(methodName);

                if (paymentMethodDw != null) {
                    paymentMethodKey = paymentMethodDw.getPaymentMethodKey();
                }
            }
        }

        // ===== REVIEW =====

        Review review = reviewRepo
                .findByReservationReservationId(res.getReservationId())
                .orElse(null);

        boolean hasReview = review != null;
        Integer reviewRating = hasReview ? review.getRating() : null;
        boolean hasComment = hasReview &&
                review.getCommentReview() != null &&
                !review.getCommentReview().isBlank();

        // ===== FACT INSERT =====

        List<ReservationRoom> reservationRooms =
                reservationRoomRepo.findByReservationReservationId(res.getReservationId());

        for (ReservationRoom rr : reservationRooms) {

            DimRoomType roomTypeDw =
                    dimRoomTypeRepo.findByRoomTypeIdOltp(rr.getRoom().getRoomType().getRoomTypeId());

            BigDecimal roomAmount =
                    rr.getFinalPricePerNight().multiply(BigDecimal.valueOf(nights));

            FactReservationSummary fact = new FactReservationSummary();

            fact.setReservationIdOltp(res.getReservationId());
            fact.setReservationRoomIdOltp(rr.getReservationRoomId());

            fact.setHotelKey(hotelDw.getHotelKey());
            fact.setGuestKey(guestDw.getGuestKey());
            fact.setChannelKey(channelDw.getChannelKey());
            fact.setStatusKey(statusDw.getStatusKey());
            fact.setRoomTypeKey(roomTypeDw.getRoomTypeKey());
            fact.setPaymentMethodKey(paymentMethodKey);

            fact.setCheckInDateKey(checkInDate.getDateKey());
            fact.setCheckOutDateKey(checkOutDate.getDateKey());
            fact.setCreatedDateKey(createdDate.getDateKey());

            fact.setAdultsCount(res.getAdultsCount());
            fact.setChildrenCount(res.getChildrenCount());
            fact.setNightsCount(nights);

            fact.setRoomAmount(roomAmount);
            fact.setTotalPaymentAmount(totalPaymentAmount);

            fact.setHasReview(hasReview ? 1 : 0);
            fact.setReviewRating(reviewRating);
            fact.setHasComment(hasComment ? 1 : 0);

            factReservationRepo.save(fact);
        }
    }

    private DimDate getDate(LocalDate date) {

        int dateKey = date.getYear() * 10000
                + date.getMonthValue() * 100
                + date.getDayOfMonth();

        return dimDateRepo.findById(dateKey)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "DIM_DATE lipseste pentru date_key = " + dateKey
                        )
                );
    }
    @Transactional("dwTransactionManager")
    public ReservationValidationDto validateReservationPropagation(Integer reservationId) {

        Reservation res = reservationRepo.findById(reservationId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Reservation not found: " + reservationId)
                );

        ReservationValidationDto.OltpSide oltp = new ReservationValidationDto.OltpSide();
        oltp.setReservationId(res.getReservationId());
        oltp.setGuestId(res.getGuest().getGuestId());
        oltp.setHotelId(res.getHotel().getHotelId());
        oltp.setBookingChannel(res.getBookingChannel().name());
        oltp.setStatus(res.getStatus().name());
        oltp.setAdultsCount(res.getAdultsCount());
        oltp.setChildrenCount(res.getChildrenCount());

        int nights = (int) ChronoUnit.DAYS.between(
                res.getCheckInDate(),
                res.getCheckOutDate()
        );
        oltp.setNightsCount(nights);

        Payment payment = paymentRepo
                .findByReservationReservationId(res.getReservationId())
                .orElse(null);

        if (payment != null) {
            oltp.setTotalPaymentAmount(payment.getAmount());
        } else {
            oltp.setTotalPaymentAmount(null);
        }

        Review review = reviewRepo
                .findByReservationReservationId(res.getReservationId())
                .orElse(null);

        boolean hasReview = review != null;
        oltp.setHasReview(hasReview);

        List<FactReservationSummary> facts =
                factReservationRepo.findByReservationIdOltp(reservationId);

        List<ReservationValidationDto.DwFactRow> dwRows = facts.stream().map(f -> {
            ReservationValidationDto.DwFactRow row = new ReservationValidationDto.DwFactRow();
            row.setReservationKey(f.getReservationKey());
            row.setReservationRoomIdOltp(f.getReservationRoomIdOltp());
            row.setHotelKey(f.getHotelKey());
            row.setGuestKey(f.getGuestKey());
            row.setChannelKey(f.getChannelKey());
            row.setStatusKey(f.getStatusKey());
            row.setRoomTypeKey(f.getRoomTypeKey());
            row.setPaymentMethodKey(f.getPaymentMethodKey());
            row.setCheckInDateKey(f.getCheckInDateKey());
            row.setCheckOutDateKey(f.getCheckOutDateKey());
            row.setCreatedDateKey(f.getCreatedDateKey());
            row.setAdultsCount(f.getAdultsCount());
            row.setChildrenCount(f.getChildrenCount());
            row.setNightsCount(f.getNightsCount());
            row.setRoomAmount(f.getRoomAmount());
            row.setTotalPaymentAmount(f.getTotalPaymentAmount());
            row.setHasReview(f.getHasReview());
            row.setReviewRating(f.getReviewRating());
            row.setHasComment(f.getHasComment());
            return row;
        }).collect(Collectors.toList());

        ReservationValidationDto dto = new ReservationValidationDto();
        dto.setOltp(oltp);
        dto.setDwFacts(dwRows);

        return dto;
    }

}
