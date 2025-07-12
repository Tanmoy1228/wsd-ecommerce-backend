package com.wsd.ecommerce_app.controller;

import com.wsd.ecommerce_app.dto.SaleTotalTodayDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @GetMapping("/today")
    public ResponseEntity<SaleTotalTodayDTO> getTodayTotalSales() {
        SaleTotalTodayDTO dto = new SaleTotalTodayDTO(LocalDate.now(), new BigDecimal("1500.75"));
        return ResponseEntity.ok(dto);
    }
}
