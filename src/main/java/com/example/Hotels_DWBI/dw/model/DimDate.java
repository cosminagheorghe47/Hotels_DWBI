package com.example.Hotels_DWBI.dw.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "DIM_DATE")
@Data
public class DimDate {

    @Id
    @Column(name = "date_key", nullable = false)
    private Integer dateKey;

    @Column(name = "full_date", nullable = false)
    private LocalDate fullDate;

    @Column(name = "day_no", nullable = false)
    private Integer dayNo;

    @Column(name = "month_no", nullable = false)
    private Integer monthNo;

    @Column(name = "month_name", nullable = false)
    private String monthName;

    @Column(name = "quarter_no", nullable = false)
    private Integer quarterNo;

    @Column(name = "year_no", nullable = false)
    private Integer yearNo;

    @Column(name = "is_weekend", nullable = false)
    private Integer isWeekend;

    public Integer getDateKey() {
        return dateKey;
    }

    public void setDateKey(Integer dateKey) {
        this.dateKey = dateKey;
    }

    public LocalDate getFullDate() {
        return fullDate;
    }

    public void setFullDate(LocalDate fullDate) {
        this.fullDate = fullDate;
    }

    public Integer getDayNo() {
        return dayNo;
    }

    public void setDayNo(Integer dayNo) {
        this.dayNo = dayNo;
    }

    public Integer getMonthNo() {
        return monthNo;
    }

    public void setMonthNo(Integer monthNo) {
        this.monthNo = monthNo;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public Integer getQuarterNo() {
        return quarterNo;
    }

    public void setQuarterNo(Integer quarterNo) {
        this.quarterNo = quarterNo;
    }

    public Integer getYearNo() {
        return yearNo;
    }

    public void setYearNo(Integer yearNo) {
        this.yearNo = yearNo;
    }

    public Integer getIsWeekend() {
        return isWeekend;
    }

    public void setIsWeekend(Integer isWeekend) {
        this.isWeekend = isWeekend;
    }
}
