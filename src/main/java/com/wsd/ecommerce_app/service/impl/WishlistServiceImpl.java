package com.wsd.ecommerce_app.service.impl;

import com.wsd.ecommerce_app.dto.WishlistItemDto;
import com.wsd.ecommerce_app.service.WishlistService;
import com.wsd.ecommerce_app.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public List<WishlistItemDto> getWishlistForCustomer(Long customerId) {
        return wishlistRepository.getWishlistForCustomer(customerId);
    }
}
