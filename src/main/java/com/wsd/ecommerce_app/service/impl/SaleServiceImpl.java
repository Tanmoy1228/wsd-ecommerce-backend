package com.wsd.ecommerce_app.service.impl;

import com.wsd.ecommerce_app.dto.SaleTotalTodayDTO;
import com.wsd.ecommerce_app.service.SaleService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class SaleServiceImpl implements SaleService {

    @Override
    public SaleTotalTodayDTO getTodaySaleTotal() {
        return new SaleTotalTodayDTO(LocalDate.now(), new BigDecimal("1500.75"));
    }
}
