package com.wsd.ecommerce_app.service;

import com.wsd.ecommerce_app.dto.MaxSaleDayDTO;
import com.wsd.ecommerce_app.dto.SaleTotalTodayDTO;
import com.wsd.ecommerce_app.repository.MaxSaleDayProjection;
import com.wsd.ecommerce_app.repository.SaleRepository;
import com.wsd.ecommerce_app.service.impl.SaleServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {

    @InjectMocks
    private SaleServiceImpl saleService;

    @Mock
    private SaleRepository saleRepository;

    @Test
    void shouldReturnTodayTotalSales() {

        LocalDate today = LocalDate.now();

        BigDecimal totalAmount = new BigDecimal("999.99");

        when(saleRepository.findTotalSaleAmountForDate(today))
                .thenReturn(totalAmount);

        SaleTotalTodayDTO dto = saleService.getTodaySaleTotal();

        assertThat(dto.getDate()).isEqualTo(today);
        assertThat(dto.getTotalSaleAmount()).isEqualTo(totalAmount);
    }

    @Test
    void shouldReturnMaxSaleDayWithinRange() {

        LocalDate startDate = LocalDate.of(2025, 6, 1);
        LocalDate endDate = LocalDate.of(2025, 7, 3);

        BigDecimal totalSaleAmount = new BigDecimal("300.00");
        LocalDate maxSaleDate = LocalDate.of(2025, 6, 5);

        when(saleRepository.findMaxSaleDay(startDate, endDate)).thenReturn(
                new MaxSaleDayProjection() {
                    @Override
                    public LocalDate getDate() {
                        return maxSaleDate;
                    }

                    @Override
                    public BigDecimal getTotalSaleAmount() {
                        return totalSaleAmount;
                    }
                }
        );

        MaxSaleDayDTO result = saleService.getMaxSaleDay(startDate, endDate);

        assertThat(result.getDate()).isEqualTo(maxSaleDate);
        assertThat(result.getTotalSaleAmount()).isEqualByComparingTo(totalSaleAmount);
    }

    @Test
    void shouldReturnNullIfNoSalesInRange() {

        LocalDate startDate = LocalDate.of(2024, 8, 1);
        LocalDate endDate = LocalDate.of(2024, 8, 2);

        when(saleRepository.findMaxSaleDay(startDate, endDate))
                .thenReturn(null);

        MaxSaleDayDTO result = saleService.getMaxSaleDay(startDate, endDate);

        assertThat(result).isNull();
    }
}
