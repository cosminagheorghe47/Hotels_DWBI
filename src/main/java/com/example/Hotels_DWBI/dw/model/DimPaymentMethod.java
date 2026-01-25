package com.example.Hotels_DWBI.dw.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DIM_PAYMENT_METHOD")
@Data
public class DimPaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_key", nullable = false)
    private Integer paymentMethodKey;

    @Column(name = "method_name", nullable = false)
    private String methodName;
}
