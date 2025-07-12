package com.wsd.ecommerce_app.service;

import com.wsd.ecommerce_app.dto.WishlistItemDto;

import java.util.List;

public interface WishlistService {

    List<WishlistItemDto> getWishlistForCustomer(Long customerId);
}
