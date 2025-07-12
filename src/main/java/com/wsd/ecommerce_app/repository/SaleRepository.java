package com.wsd.ecommerce_app.repository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public class SaleRepository {

    public BigDecimal findTotalSaleAmountForDateRange(LocalDateTime startOfDay, LocalDateTime endOfDay) {
        return new BigDecimal("999.99");
    }


}
