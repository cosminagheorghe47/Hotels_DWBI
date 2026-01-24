package com.example.Hotels_DWBI.oltp.controller;

import com.example.Hotels_DWBI.oltp.model.RoomType;
import com.example.Hotels_DWBI.oltp.service.RoomTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room-types")
public class RoomTypeController {

    private final RoomTypeService roomTypeService;

    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @GetMapping
    public List<RoomType> getAll() {
        return roomTypeService.findAll();
    }

    @GetMapping("/{id}")
    public RoomType getById(@PathVariable Integer id) {
        return roomTypeService.findById(id);
    }

    @PostMapping
    public RoomType create(@RequestBody RoomType roomType) {
        return roomTypeService.save(roomType);
    }

    @PutMapping("/{id}")
    public RoomType update(@PathVariable Integer id, @RequestBody RoomType roomType) {
        return roomTypeService.update(id, roomType);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        roomTypeService.delete(id);
    }
}
