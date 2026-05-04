package com.example.Hotels_DWBI.dw.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DIM_RESERVATION_STATUS")
@Data
public class DimReservationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_key", nullable = false)
    private Integer statusKey;

    @Column(name = "status_name", nullable = false)
    private String statusName;

    public Integer getStatusKey() {
        return statusKey;
    }

    public void setStatusKey(Integer statusKey) {
        this.statusKey = statusKey;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
