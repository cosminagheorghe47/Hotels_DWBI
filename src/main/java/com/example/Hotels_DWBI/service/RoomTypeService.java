package com.example.Hotels_DWBI.service;

import com.example.Hotels_DWBI.model.RoomType;
import com.example.Hotels_DWBI.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public List<RoomType> findAll() {
        return roomTypeRepository.findAll();
    }

    public RoomType findById(Integer id) {
        return roomTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RoomType not found with id " + id));
    }

    public RoomType save(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    public RoomType update(Integer id, RoomType updated) {
        RoomType existing = findById(id);

        existing.setName(updated.getName());
        existing.setMaxAdults(updated.getMaxAdults());
        existing.setMaxChildren(updated.getMaxChildren());
        existing.setBasePricePerNight(updated.getBasePricePerNight());
        existing.setCurrency(updated.getCurrency());

        return roomTypeRepository.save(existing);
    }

    public void delete(Integer id) {
        roomTypeRepository.deleteById(id);
    }
}
