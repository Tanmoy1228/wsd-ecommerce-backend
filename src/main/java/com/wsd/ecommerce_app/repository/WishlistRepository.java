package com.wsd.ecommerce_app.repository;

import com.wsd.ecommerce_app.dto.WishlistItemDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class WishlistRepository {

    public List<WishlistItemDto> getWishlistForCustomer(Long customerId) {

        if (customerId.equals(999L)) {
            return List.of();
        }

        return List.of(
                new WishlistItemDto(100L, "Sample wishlist item", LocalDateTime.now())
        );
    }
}
