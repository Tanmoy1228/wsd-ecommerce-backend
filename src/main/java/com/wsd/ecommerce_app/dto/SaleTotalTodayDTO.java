package com.wsd.ecommerce_app.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SaleTotalTodayDTO {

    private LocalDate date;
    private BigDecimal totalSaleAmount;

    public SaleTotalTodayDTO() {}

    public SaleTotalTodayDTO(LocalDate date, BigDecimal totalSaleAmount) {
        this.date = date;
        this.totalSaleAmount = totalSaleAmount;
    }
    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public BigDecimal getTotalSaleAmount() { return totalSaleAmount; }

    public void setTotalSaleAmount(BigDecimal totalSaleAmount) { this.totalSaleAmount = totalSaleAmount; }
}
