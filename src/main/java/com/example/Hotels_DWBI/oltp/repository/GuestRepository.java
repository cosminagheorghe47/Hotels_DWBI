package com.example.Hotels_DWBI.oltp.repository;

import com.example.Hotels_DWBI.oltp.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Integer> {
}