package com.wsd.ecommerce_app.controller;

import com.wsd.ecommerce_app.dto.SaleTotalTodayDTO;
import com.wsd.ecommerce_app.service.SaleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SaleController.class)
public class SaleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaleService saleService;

    @Test
    void shouldReturnTodayTotalSales() throws Exception {

        LocalDate today = LocalDate.now();
        BigDecimal totalSaleAmount = new BigDecimal("1500.75");

        SaleTotalTodayDTO saleTotalTodayDTO = new SaleTotalTodayDTO(today, totalSaleAmount);

        Mockito.when(saleService.getTodaySaleTotal()).thenReturn(saleTotalTodayDTO);

        mockMvc.perform(get("/api/sales/today")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value(today.toString()))
                .andExpect(jsonPath("$.totalSaleAmount").value(totalSaleAmount));
    }
}
