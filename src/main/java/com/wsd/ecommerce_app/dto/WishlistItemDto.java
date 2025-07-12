package com.wsd.ecommerce_app.dto;

import java.time.LocalDateTime;

public class WishlistItemDto {

    private Long productId;
    private String productName;
    private LocalDateTime addedDate;

    public WishlistItemDto() {}

    public WishlistItemDto(Long productId, String productName, LocalDateTime addedDate) {
        setProductId(productId);
        setProductName(productName);
        setAddedDate(addedDate);
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

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }
}
