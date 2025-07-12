package com.wsd.ecommerce_app.controller;

import com.wsd.ecommerce_app.dto.MaxSaleDayDTO;
import com.wsd.ecommerce_app.dto.SaleTotalTodayDTO;
import com.wsd.ecommerce_app.dto.TopSellingItemDTO;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
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

    @Test
    void shouldReturnMaxSaleDayForGivenRange() throws Exception {

        LocalDate startDate = LocalDate.of(2024, 7, 1);
        LocalDate endDate = LocalDate.of(2024, 7, 10);

        BigDecimal totalSaleAmount = new BigDecimal("999.99");
        LocalDate date = LocalDate.of(2024, 7, 5);

        MaxSaleDayDTO dto = new MaxSaleDayDTO(date, totalSaleAmount);

        Mockito.when(saleService.getMaxSaleDay(eq(startDate), eq(endDate))).thenReturn(dto);

        mockMvc.perform(get("/api/sales/max-day")
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value(dto.getDate().toString()))
                .andExpect(jsonPath("$.totalSaleAmount").value(dto.getTotalSaleAmount()));
    }

    @Test
    void shouldReturnBadRequestWhenEndDateIsBeforeStartDate() throws Exception {
        mockMvc.perform(get("/api/sales/max-day")
                        .param("startDate", "2024-07-10")
                        .param("endDate", "2024-07-01"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestForInvalidDateFormat() throws Exception {
        mockMvc.perform(get("/api/sales/max-day")
                        .param("startDate", "07-01-2024")
                        .param("endDate", "2024-07-10"))
                .andExpect(status().isBadRequest());
    }


    @Test
    void shouldReturnBadRequestWhenStartDateIsMissing() throws Exception {
        mockMvc.perform(get("/api/sales/max-day")
                        .param("endDate", "2024-07-10"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequestWhenEndDateIsMissing() throws Exception {
        mockMvc.perform(get("/api/sales/max-day")
                        .param("startDate", "2024-07-01"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnTop5SellingItemsOfAllTime() throws Exception {

        List<TopSellingItemDTO> topSellingItems = List.of(
                new TopSellingItemDTO(1L, "Laptop", new BigDecimal("9999.99")),
                new TopSellingItemDTO(2L, "Phone", new BigDecimal("8888.88")),
                new TopSellingItemDTO(3L, "Tablet", new BigDecimal("7777.77")),
                new TopSellingItemDTO(4L, "Monitor", new BigDecimal("6666.66")),
                new TopSellingItemDTO(5L, "Mouse", new BigDecimal("5555.55"))
        );

        Mockito.when(saleService.getTop5SellingItems()).thenReturn(topSellingItems);

        mockMvc.perform(get("/api/sales/top-five-selling-items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldReturnTop5ItemsOfLastMonthByNumberOfSales() throws Exception {

        mockMvc.perform(get("/api/sales/last-month-top-selling-items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

}
