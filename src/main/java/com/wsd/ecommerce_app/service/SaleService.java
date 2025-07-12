package com.wsd.ecommerce_app.service;

import com.wsd.ecommerce_app.dto.MaxSaleDayDTO;
import com.wsd.ecommerce_app.dto.SaleTotalTodayDTO;
import com.wsd.ecommerce_app.dto.TopItemLastMonthDTO;
import com.wsd.ecommerce_app.dto.TopSellingItemDTO;

import java.time.LocalDate;
import java.util.List;

public interface SaleService {

    SaleTotalTodayDTO getTodaySaleTotal();

    MaxSaleDayDTO getMaxSaleDay(LocalDate startDate, LocalDate endDate);

    List<TopSellingItemDTO> getTop5SellingItems();

    List<TopItemLastMonthDTO> getTop5ItemsOfLastMonth();
}
