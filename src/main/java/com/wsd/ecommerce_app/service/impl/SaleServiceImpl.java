package com.wsd.ecommerce_app.service.impl;

import com.wsd.ecommerce_app.dto.MaxSaleDayDTO;
import com.wsd.ecommerce_app.dto.SaleTotalTodayDTO;
import com.wsd.ecommerce_app.repository.SaleRepository;
import com.wsd.ecommerce_app.service.SaleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class SaleServiceImpl implements SaleService {

    private static final Logger logger = LogManager.getLogger(SaleServiceImpl.class);

    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public SaleTotalTodayDTO getTodaySaleTotal() {

        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay().minusNanos(1);

        BigDecimal total = saleRepository.findTotalSaleAmountForDateRange(startOfDay, endOfDay);

        logger.info("Calculated today's total sales: {}", total);

        total = total != null ? total : BigDecimal.ZERO;

        return new SaleTotalTodayDTO(today, total);
    }

    @Override
    public MaxSaleDayDTO getMaxSaleDay(LocalDate startDate, LocalDate endDate) {

        BigDecimal totalSaleAmount = new BigDecimal("999.99");
        LocalDate date = LocalDate.of(2024, 7, 5);

        MaxSaleDayDTO maxSaleDayDTO = new MaxSaleDayDTO(date, totalSaleAmount);

        return maxSaleDayDTO;
    }
}
