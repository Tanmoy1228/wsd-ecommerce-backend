package com.wsd.ecommerce_app.service.impl;

import com.wsd.ecommerce_app.dto.WishlistItemDto;
import com.wsd.ecommerce_app.model.Wishlist;
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

        List<Wishlist> wishlists = wishlistRepository.findByCustomer_Id(customerId);

        return wishlists.stream()
            .map(wishlist -> new WishlistItemDto(
                wishlist.getProduct().getId(),
                wishlist.getProduct().getName(),
                wishlist.getAddedDate()))
            .toList();
    }
}
