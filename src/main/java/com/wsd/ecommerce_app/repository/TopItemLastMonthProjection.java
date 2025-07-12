package com.wsd.ecommerce_app.repository;

import java.math.BigDecimal;

public interface TopItemLastMonthProjection {

    Long getProductId();
    String getProductName();
    Integer getNumberOfSales();
}
