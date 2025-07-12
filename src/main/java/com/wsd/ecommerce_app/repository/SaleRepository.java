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

    @Query("""
    SELECT
        s.product.id AS productId,
        s.product.name AS productName,
        SUM(s.amount) AS totalSaleAmount
    FROM Sale s
    GROUP BY s.product.id
    ORDER BY SUM(s.amount) DESC
    LIMIT 5
    """)
    List<TopSellingItemProjection> findTop5SellingItems();

    @Query("""
    SELECT
        s.product.id AS productId,
        s.product.name AS productName,
        SUM(s.quantity) AS numberOfSales
    FROM Sale s
    WHERE s.saleDate >= :startDate AND s.saleDate <= :endDate
    GROUP BY s.product.id
    ORDER BY SUM(s.quantity) DESC
    LIMIT 5
    """)
    List<TopItemLastMonthProjection> findTop5SellingItemsOfLastMonth(LocalDate firstDayOfLastMonth, LocalDate lastDayOfLastMonth);
}
