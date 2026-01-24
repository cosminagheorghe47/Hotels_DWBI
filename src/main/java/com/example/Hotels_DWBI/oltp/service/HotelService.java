package com.example.Hotels_DWBI.oltp.service;

import com.example.Hotels_DWBI.oltp.model.Hotel;
import com.example.Hotels_DWBI.oltp.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Integer id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id " + id));
    }

    public Hotel createHotel(Hotel hotel) {
        hotel.setCreatedAt(LocalDateTime.now());
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Integer id, Hotel updatedHotel) {
        Hotel hotel = getHotelById(id);

        hotel.setName(updatedHotel.getName());
        hotel.setStars(updatedHotel.getStars());
        hotel.setCountry(updatedHotel.getCountry());
        hotel.setCity(updatedHotel.getCity());
        hotel.setAddress(updatedHotel.getAddress());
        hotel.setPhone(updatedHotel.getPhone());
        hotel.setEmail(updatedHotel.getEmail());

        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Integer id) {
        Hotel hotel = getHotelById(id);
        hotelRepository.delete(hotel);
    }
}
