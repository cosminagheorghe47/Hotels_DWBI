package com.example.Hotels_DWBI.dw.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DIM_BOOKING_CHANNEL")
@Data
public class DimBookingChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_key", nullable = false)
    private Integer channelKey;

    @Column(name = "channel_name")
    private String channelName;
}
