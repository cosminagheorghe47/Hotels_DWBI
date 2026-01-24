package com.example.Hotels_DWBI.dw.repository;

import com.example.Hotels_DWBI.dw.model.DimPaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimPaymentMethodRepository extends JpaRepository<DimPaymentMethod, Integer> {
    DimPaymentMethod findByMethodName(String methodName);
}
