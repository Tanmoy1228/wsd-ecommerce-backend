package com.wsd.ecommerce_app.repository;

import com.wsd.ecommerce_app.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT COALESCE(SUM(s.amount), 0) FROM Sale s WHERE s.saleDate = :today")
    BigDecimal findTotalSaleAmountForDate(LocalDate today);

    @Query("""
    SELECT
      s.saleDate AS date,
      SUM(s.amount) as totalSaleAmount
    FROM Sale s
    WHERE s.saleDate >= :startDate AND s.saleDate <= :endDate
    GROUP BY s.saleDate
    ORDER BY SUM(s.amount) DESC, s.saleDate DESC limit 1
   """)
    MaxSaleDayProjection findMaxSaleDay(LocalDate startDate, LocalDate endDate);

    List<TopSellingItemProjection> findTop5SellingItems();
}
