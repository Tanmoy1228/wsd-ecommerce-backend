package com.wsd.ecommerce_app.service.impl;

import com.wsd.ecommerce_app.dto.MaxSaleDayDTO;
import com.wsd.ecommerce_app.dto.SaleTotalTodayDTO;
import com.wsd.ecommerce_app.dto.TopItemLastMonthDTO;
import com.wsd.ecommerce_app.dto.TopSellingItemDTO;
import com.wsd.ecommerce_app.repository.MaxSaleDayProjection;
import com.wsd.ecommerce_app.repository.SaleRepository;
import com.wsd.ecommerce_app.repository.TopSellingItemProjection;
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

        List<TopSellingItemProjection> topSellingItemProjection = saleRepository.findTop5SellingItems();

        return topSellingItemProjection.stream()
                .map(projection ->
                        new TopSellingItemDTO(
                                projection.getProductId(),
                                projection.getProductName(),
                                projection.getTotalSaleAmount()
                        )
                ).toList();
    }

    @Override
    public List<TopItemLastMonthDTO> getTop5ItemsOfLastMonth() {

        return List.of(
                new TopItemLastMonthDTO(1L, "Laptop", 10),
                new TopItemLastMonthDTO(2L, "Phone", 20),
                new TopItemLastMonthDTO(3L, "Tablet", 30),
                new TopItemLastMonthDTO(4L, "Monitor", 40),
                new TopItemLastMonthDTO(5L, "Mouse", 50)
        );
    }
}
