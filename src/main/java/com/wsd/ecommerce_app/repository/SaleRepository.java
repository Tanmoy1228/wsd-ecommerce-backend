package com.wsd.ecommerce_app.repository;

import com.wsd.ecommerce_app.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT COALESCE(SUM(s.amount), 0) FROM Sale s WHERE s.saleDate = :today")
    BigDecimal findTotalSaleAmountForDate(LocalDate today);
}
