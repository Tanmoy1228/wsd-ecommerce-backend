package com.wsd.ecommerce_app.repository;

import java.math.BigDecimal;

public interface TopSellingItemProjection {
    Long getProductId();
    String getProductName();
    BigDecimal getTotalSaleAmount();
}
