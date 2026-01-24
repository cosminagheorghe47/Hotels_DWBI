package com.example.Hotels_DWBI.dw.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "DIM_DATE")
@Data
public class DimDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_key", nullable = false)
    private Integer dateKey;

    @Column(name = "full_date", nullable = false)
    private LocalDate fullDate;

    @Column(name = "day_no")
    private Integer dayNo;

    @Column(name = "month_no")
    private Integer monthNo;

    @Column(name = "month_name")
    private String monthName;

    @Column(name = "quarter_no")
    private Integer quarterNo;

    @Column(name = "year_no")
    private Integer yearNo;

    @Column(name = "is_weekend")
    private Integer isWeekend;
}
