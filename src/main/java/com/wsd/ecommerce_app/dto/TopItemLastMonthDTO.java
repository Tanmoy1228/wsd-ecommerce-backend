package com.wsd.ecommerce_app.dto;

public class TopItemLastMonthDTO {

    private Long productId;

    private String productName;

    private Integer numberOfSales;

    public TopItemLastMonthDTO() {}

    public TopItemLastMonthDTO(Long productId, String productName, Integer numberOfSales) {
        this.productId = productId;
        this.productName = productName;
        this.numberOfSales = numberOfSales;
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

    public Integer getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(Integer numberOfSales) {
        this.numberOfSales = numberOfSales;
    }
}
