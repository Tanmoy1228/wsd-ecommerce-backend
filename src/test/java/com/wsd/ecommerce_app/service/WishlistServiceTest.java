package com.wsd.ecommerce_app.service;

import com.wsd.ecommerce_app.dto.WishlistItemDto;
import com.wsd.ecommerce_app.service.impl.WishlistServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class WishlistServiceTest {

    @InjectMocks
    private WishlistServiceImpl wishlistService;

    @Test
    void shouldReturnWishlistItemsForCustomer() {

        Long customerId = 1L;

        List<WishlistItemDto> result = wishlistService.getWishlistForCustomer(customerId);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getProductId()).isEqualTo(100L);
        assertThat(result.get(0).getProductName()).isEqualTo("Sample wishlist item");
    }

    @Test
    void shouldReturnEmptyListWhenCustomerHasNoWishlist() {

        Long customerId = 999L;

        List<WishlistItemDto> result = wishlistService.getWishlistForCustomer(customerId);

        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}
