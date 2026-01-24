package com.example.Hotels_DWBI.oltp.service;

import com.example.Hotels_DWBI.oltp.model.Room;
import com.example.Hotels_DWBI.oltp.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Integer id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id " + id));
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Integer id, Room updatedRoom) {
        Room room = getRoomById(id);

        room.setHotel(updatedRoom.getHotel());
        room.setRoomType(updatedRoom.getRoomType());
        room.setRoomNumber(updatedRoom.getRoomNumber());
        room.setFloorNo(updatedRoom.getFloorNo());
        room.setStatus(updatedRoom.getStatus());

        return roomRepository.save(room);
    }

    public void deleteRoom(Integer id) {
        Room room = getRoomById(id);
        roomRepository.delete(room);
    }
}
