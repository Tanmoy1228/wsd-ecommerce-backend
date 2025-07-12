package com.wsd.ecommerce_app.service.impl;

import com.wsd.ecommerce_app.dto.MaxSaleDayDTO;
import com.wsd.ecommerce_app.dto.SaleTotalTodayDTO;
import com.wsd.ecommerce_app.dto.TopSellingItemDTO;
import com.wsd.ecommerce_app.repository.MaxSaleDayProjection;
import com.wsd.ecommerce_app.repository.SaleRepository;
import com.wsd.ecommerce_app.service.SaleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

        BigDecimal total = saleRepository.findTotalSaleAmountForDate(today);

        logger.info("Calculated today's total sales: {}", total);

        total = total != null ? total : BigDecimal.ZERO;

        return new SaleTotalTodayDTO(today, total);
    }

    @Override
    public MaxSaleDayDTO getMaxSaleDay(LocalDate startDate, LocalDate endDate) {

        MaxSaleDayProjection maxSaleDayProjection = saleRepository.findMaxSaleDay(startDate, endDate);

        if (maxSaleDayProjection == null) {
            return null;
        }

        return new MaxSaleDayDTO(maxSaleDayProjection.getDate(), maxSaleDayProjection.getTotalSaleAmount());
    }

    @Override
    public List<TopSellingItemDTO> getTop5SellingItems() {

        return List.of(
                new TopSellingItemDTO(1L, "Laptop", new BigDecimal("9999.99")),
                new TopSellingItemDTO(2L, "Phone", new BigDecimal("8888.88")),
                new TopSellingItemDTO(3L, "Tablet", new BigDecimal("7777.77")),
                new TopSellingItemDTO(4L, "Monitor", new BigDecimal("6666.66")),
                new TopSellingItemDTO(5L, "Mouse", new BigDecimal("5555.55"))
        );
    }
}
