package com.wsd.ecommerce_app.controller;

import com.wsd.ecommerce_app.dto.WishlistItemDto;
import com.wsd.ecommerce_app.exception.CustomerNotFoundException;
import com.wsd.ecommerce_app.repository.CustomerRepository;
import com.wsd.ecommerce_app.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    private final CustomerRepository customerRepository;

    public WishlistController(WishlistService wishlistService, CustomerRepository customerRepository) {
        this.wishlistService = wishlistService;
        this.customerRepository = customerRepository;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<WishlistItemDto>> getWishlist(@PathVariable Long customerId) {

        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }

        List<WishlistItemDto> wishlistItemDto = wishlistService.getWishlistForCustomer(customerId);

        return ResponseEntity.ok(wishlistItemDto);
    }
}
