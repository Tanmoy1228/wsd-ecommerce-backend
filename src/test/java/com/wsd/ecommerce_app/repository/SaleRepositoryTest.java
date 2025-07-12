package com.wsd.ecommerce_app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
