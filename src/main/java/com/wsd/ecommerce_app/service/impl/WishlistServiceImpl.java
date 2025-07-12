package com.wsd.ecommerce_app.service.impl;

import com.wsd.ecommerce_app.dto.WishlistItemDto;
import com.wsd.ecommerce_app.service.WishlistService;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Override
    public List<WishlistItemDto> getWishlistForCustomer(Long customerId) {

        if (customerId.equals(999L)) {
            return List.of();
        }

        return List.of(
                new WishlistItemDto(100L, "Sample wishlist item", LocalDateTime.now())
        );
    }
}
