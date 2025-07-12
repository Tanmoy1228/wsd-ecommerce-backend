package com.wsd.ecommerce_app.controller;

import com.wsd.ecommerce_app.dto.MaxSaleDayDTO;
import com.wsd.ecommerce_app.dto.SaleTotalTodayDTO;
import com.wsd.ecommerce_app.dto.TopSellingItemDTO;
import com.wsd.ecommerce_app.service.SaleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/today")
    public ResponseEntity<SaleTotalTodayDTO> getTodayTotalSales() {

        SaleTotalTodayDTO dto = saleService.getTodaySaleTotal();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/max-day")
    public ResponseEntity<MaxSaleDayDTO> getMaxSaleDay(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        if (endDate.isBefore(startDate)) {
            return ResponseEntity.badRequest().build();
        }

        MaxSaleDayDTO maxSaleDayDTO = saleService.getMaxSaleDay(startDate, endDate);

        return ResponseEntity.ok(maxSaleDayDTO);
    }

    @GetMapping("/top-five-selling-items")
    public ResponseEntity<List<TopSellingItemDTO>> getTop5SellingItems() {

        List<TopSellingItemDTO> topSellingItems = saleService.getTop5SellingItems();

        return ResponseEntity.ok(topSellingItems);
    }
}
