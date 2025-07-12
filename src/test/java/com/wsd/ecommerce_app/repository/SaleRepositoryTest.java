package com.wsd.ecommerce_app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class SaleRepositoryTest {

    @Autowired
    private SaleRepository saleRepository;

    @Test
    void shouldSumAmountBySaleDate() {

        LocalDate today = LocalDate.now();

        BigDecimal totalAmount = saleRepository.findTotalSaleAmountForDate(today);

        assertThat(totalAmount).isNotNull();
        assertThat(totalAmount).isGreaterThanOrEqualTo(BigDecimal.ZERO);
    }

    @Test
    void shouldFindSalesWithinDateRange() {

        LocalDate startDate = LocalDate.of(2025, 6, 2);
        LocalDate endDate = LocalDate.of(2025, 7, 3);

        MaxSaleDayProjection result = saleRepository.findMaxSaleDay(startDate, endDate);

        assertThat(result.getDate()).isNotNull();
        assertThat(result.getTotalSaleAmount()).isNotNull();
        assertThat(result.getTotalSaleAmount()).isGreaterThan(BigDecimal.ZERO);
    }

    @Test
    void shouldReturnEmptyWhenNoSales() {

        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 2);

        MaxSaleDayProjection result = saleRepository.findMaxSaleDay(startDate, endDate);

        assertThat(result).isNull();
    }
}
