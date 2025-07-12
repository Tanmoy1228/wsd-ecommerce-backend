package com.wsd.ecommerce_app.service;

import com.wsd.ecommerce_app.dto.MaxSaleDayDTO;
import com.wsd.ecommerce_app.dto.SaleTotalTodayDTO;

import java.time.LocalDate;

public interface SaleService {

    SaleTotalTodayDTO getTodaySaleTotal();

    MaxSaleDayDTO getMaxSaleDay(LocalDate startDate, LocalDate endDate);
}
