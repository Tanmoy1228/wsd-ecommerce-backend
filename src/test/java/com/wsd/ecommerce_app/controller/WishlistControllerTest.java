package com.wsd.ecommerce_app.controller;

import com.wsd.ecommerce_app.dto.WishlistItemDto;
import com.wsd.ecommerce_app.service.WishlistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishlistController.class)
class WishlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishlistService wishlistService;

    @Test
    void shouldReturnWishlistItemsForCustomer() throws Exception {

        WishlistItemDto wishlistItemDto = new WishlistItemDto(100L, "Sample wishlist item", LocalDateTime.now());

        when(wishlistService.getWishlistForCustomer(1L))
                .thenReturn(List.of(wishlistItemDto));

        mockMvc.perform(get("/api/wishlist/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].productId").value(100))
                .andExpect(jsonPath("$[0].productName").value("Sample wishlist item"))
                .andExpect(jsonPath("$[0].addedDate").exists());
    }
}

