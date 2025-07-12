package com.wsd.ecommerce_app.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface MaxSaleDayProjection {
    LocalDate getDate();
    BigDecimal getTotalSaleAmount();
}
