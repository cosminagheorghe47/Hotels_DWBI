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

    @Column(name = "channel_name", nullable = false)
    private String channelName;

    public Integer getChannelKey() {
        return channelKey;
    }

    public void setChannelKey(Integer channelKey) {
        this.channelKey = channelKey;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
