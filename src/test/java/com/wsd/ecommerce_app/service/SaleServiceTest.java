package com.wsd.ecommerce_app.service;

import com.wsd.ecommerce_app.dto.SaleTotalTodayDTO;
import com.wsd.ecommerce_app.service.impl.SaleServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {

    @InjectMocks
    private SaleServiceImpl saleService;

    @Test
    void shouldReturnTodayTotalSales() {

        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay().minusNanos(1);

        BigDecimal totalAmount = new BigDecimal("999.99");

        SaleTotalTodayDTO dto = saleService.getTodaySaleTotal();

        assertThat(dto.getDate()).isEqualTo(today);
        assertThat(dto.getTotalSaleAmount()).isEqualTo(totalAmount);
    }
}
