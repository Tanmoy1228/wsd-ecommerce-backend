package com.wsd.ecommerce_app.dto;

import java.math.BigDecimal;

public class TopSellingItemDTO {

    private Long productId;

    private String productName;

    private BigDecimal totalSaleAmount;

    public TopSellingItemDTO() {}

    public TopSellingItemDTO(Long productId, String productName, BigDecimal totalSaleAmount) {
        this.productId = productId;
        this.productName = productName;
        this.totalSaleAmount = totalSaleAmount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getTotalSaleAmount() {
        return totalSaleAmount;
    }

    public void setTotalSaleAmount(BigDecimal totalSaleAmount) {
        this.totalSaleAmount = totalSaleAmount;
    }
}
