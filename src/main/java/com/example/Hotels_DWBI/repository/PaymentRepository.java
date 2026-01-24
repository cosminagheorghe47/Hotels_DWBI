package com.example.Hotels_DWBI.repository;

import com.example.Hotels_DWBI.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
