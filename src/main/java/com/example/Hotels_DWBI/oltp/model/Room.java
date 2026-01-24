package com.example.Hotels_DWBI.oltp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ROOMS")
@Data
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Integer roomId;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Column(name = "floor_no")
    private Integer floorNo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomStatus status;
}
