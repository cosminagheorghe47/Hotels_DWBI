package com.example.Hotels_DWBI.dw.repository;

import com.example.Hotels_DWBI.dw.model.DimBookingChannel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimBookingChannelRepository extends JpaRepository<DimBookingChannel, Integer> {
    DimBookingChannel findByChannelName(String channelName);
}
