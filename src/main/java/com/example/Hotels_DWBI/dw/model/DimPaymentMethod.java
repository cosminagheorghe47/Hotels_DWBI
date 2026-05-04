package com.example.Hotels_DWBI.dw.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DIM_PAYMENT_METHOD")
@Data
public class DimPaymentMethod {

    @Id
    @Column(name = "payment_method_key", nullable = false)
    private Integer paymentMethodKey;

    @Column(name = "method_name", nullable = false)
    private String methodName;

    public Integer getPaymentMethodKey() {
        return paymentMethodKey;
    }

    public void setPaymentMethodKey(Integer paymentMethodKey) {
        this.paymentMethodKey = paymentMethodKey;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
