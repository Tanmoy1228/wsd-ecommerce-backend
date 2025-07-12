package com.wsd.ecommerce_app.controller;

import com.wsd.ecommerce_app.dto.WishlistItemDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @GetMapping("/{customerId}")
    public ResponseEntity<List<WishlistItemDto>> getWishlist(@PathVariable Long customerId) {

        List<WishlistItemDto> wishlistItemDto = List.of(
                new WishlistItemDto(100L, "Sample wishlist item", java.time.LocalDateTime.now())
        );

        return ResponseEntity.ok(wishlistItemDto);
    }
}
