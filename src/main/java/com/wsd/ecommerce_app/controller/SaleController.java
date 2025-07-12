package com.wsd.ecommerce_app.controller;

import com.wsd.ecommerce_app.dto.SaleTotalTodayDTO;
import com.wsd.ecommerce_app.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
