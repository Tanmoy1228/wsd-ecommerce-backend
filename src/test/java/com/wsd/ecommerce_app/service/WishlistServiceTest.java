package com.wsd.ecommerce_app.service;

import com.wsd.ecommerce_app.dto.WishlistItemDto;
import com.wsd.ecommerce_app.repository.WishlistRepository;
import com.wsd.ecommerce_app.service.impl.WishlistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WishlistServiceTest {

    @InjectMocks
    private WishlistServiceImpl wishlistService;

    @Mock
    private WishlistRepository wishlistRepository;

    private WishlistItemDto sampleWishlistItem;

    @BeforeEach
    void setUp() {
        sampleWishlistItem = new WishlistItemDto(100L, "Sample wishlist item", LocalDateTime.now());
    }

    @Test
    void shouldReturnWishlistItemsForCustomer() {

        Long customerId = 1L;

        when(wishlistRepository.getWishlistForCustomer(customerId))
                .thenReturn(List.of(sampleWishlistItem));

        List<WishlistItemDto> result = wishlistService.getWishlistForCustomer(customerId);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getProductId()).isEqualTo(100L);
        assertThat(result.get(0).getProductName()).isEqualTo("Sample wishlist item");
    }

    @Test
    void shouldReturnEmptyListWhenCustomerHasNoWishlist() {

        Long customerId = 999L;

        when(wishlistRepository.getWishlistForCustomer(customerId))
                .thenReturn(List.of());

        List<WishlistItemDto> result = wishlistService.getWishlistForCustomer(customerId);

        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}
