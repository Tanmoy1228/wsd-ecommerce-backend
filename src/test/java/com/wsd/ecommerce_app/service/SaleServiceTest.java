package com.wsd.ecommerce_app.service;

import com.wsd.ecommerce_app.dto.MaxSaleDayDTO;
import com.wsd.ecommerce_app.dto.SaleTotalTodayDTO;
import com.wsd.ecommerce_app.dto.TopSellingItemDTO;
import com.wsd.ecommerce_app.repository.MaxSaleDayProjection;
import com.wsd.ecommerce_app.repository.SaleRepository;
import com.wsd.ecommerce_app.repository.TopSellingItemProjection;
import com.wsd.ecommerce_app.service.impl.SaleServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {

    @InjectMocks
    private SaleServiceImpl saleService;

    @Mock
    private SaleRepository saleRepository;

    @Test
    void shouldReturnTodayTotalSales() {

        LocalDate today = LocalDate.now();

        BigDecimal totalAmount = new BigDecimal("999.99");

        when(saleRepository.findTotalSaleAmountForDate(today))
                .thenReturn(totalAmount);

        SaleTotalTodayDTO dto = saleService.getTodaySaleTotal();

        assertThat(dto.getDate()).isEqualTo(today);
        assertThat(dto.getTotalSaleAmount()).isEqualTo(totalAmount);
    }

    @Test
    void shouldReturnMaxSaleDayWithinRange() {

        LocalDate startDate = LocalDate.of(2025, 6, 1);
        LocalDate endDate = LocalDate.of(2025, 7, 3);

        BigDecimal totalSaleAmount = new BigDecimal("300.00");
        LocalDate maxSaleDate = LocalDate.of(2025, 6, 5);

        when(saleRepository.findMaxSaleDay(startDate, endDate)).thenReturn(
                new MaxSaleDayProjection() {
                    @Override
                    public LocalDate getDate() {
                        return maxSaleDate;
                    }

                    @Override
                    public BigDecimal getTotalSaleAmount() {
                        return totalSaleAmount;
                    }
                }
        );

        MaxSaleDayDTO result = saleService.getMaxSaleDay(startDate, endDate);

        assertThat(result.getDate()).isEqualTo(maxSaleDate);
        assertThat(result.getTotalSaleAmount()).isEqualByComparingTo(totalSaleAmount);
    }

    @Test
    void shouldReturnNullIfNoSalesInRange() {

        LocalDate startDate = LocalDate.of(2024, 8, 1);
        LocalDate endDate = LocalDate.of(2024, 8, 2);

        when(saleRepository.findMaxSaleDay(startDate, endDate))
                .thenReturn(null);

        MaxSaleDayDTO result = saleService.getMaxSaleDay(startDate, endDate);

        assertThat(result).isNull();
    }

    @Test
    void shouldReturnTop5SellingItems() {

        List<TopSellingItemProjection> topSellingItems = List.of(
                createProjection(1L, "Laptop", new BigDecimal("9999.99")),
                createProjection(2L, "Phone", new BigDecimal("8888.88")),
                createProjection(3L, "Tablet", new BigDecimal("7777.77")),
                createProjection(4L, "Monitor", new BigDecimal("6666.66")),
                createProjection(5L, "Mouse", new BigDecimal("5555.55"))
        );

        when(saleRepository.findTop5SellingItems()).thenReturn(topSellingItems);

        List<TopSellingItemDTO> result = saleService.getTop5SellingItems();

        assertThat(result).hasSize(5);
        assertThat(result.get(0).getProductName()).isEqualTo("Laptop");
        assertThat(result.get(1).getProductName()).isEqualTo("Phone");
    }

    @Test
    void shouldReturnLessThan5ItemsWhenNotEnoughProducts() {

        List<TopSellingItemProjection> topSellingItems = Arrays.asList(
                createProjection(1L, "Laptop", new BigDecimal("5000.00")),
                createProjection(2L, "Phone", new BigDecimal("3000.00"))
        );

        when(saleRepository.findTop5SellingItems()).thenReturn(topSellingItems);

        List<TopSellingItemDTO> result = saleService.getTop5SellingItems();

        assertThat(result).hasSize(2);
    }

    @Test
    void shouldReturnEmptyListWhenNoSales() {

        when(saleRepository.findTop5SellingItems()).thenReturn(Collections.emptyList());

        List<TopSellingItemDTO> result = saleService.getTop5SellingItems();

        assertThat(result).isEmpty();
    }

    private TopSellingItemProjection createProjection(Long productId, String productName, BigDecimal totalSaleAmount) {

        return new TopSellingItemProjection() {
            @Override
            public Long getProductId() { return productId; }

            @Override
            public String getProductName() { return productName; }

            @Override
            public BigDecimal getTotalSaleAmount() { return totalSaleAmount; }
        };
    }

}
