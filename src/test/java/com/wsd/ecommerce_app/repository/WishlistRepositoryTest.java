package com.wsd.ecommerce_app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class WishlistRepositoryTest {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Test
    void shouldReturnWishlistItemsForCustomer() {

        Long customerId = 1L;

        List<Wishlist> result = wishlistRepository.getWishlistForCustomer(customerId);

        assertThat(result).isNotNull();
        assertThat(result).size().isGreaterThan(0);
    }

    @Test
    void shouldReturnEmptyListWhenCustomerHasNoWishlist() {

        Long customerId = 19L;

        List<Wishlist> result = wishlistRepository.getWishlistForCustomer(customerId);

        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}
