package com.wsd.ecommerce_app.repository;

import com.wsd.ecommerce_app.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    @Query("SELECT wi FROM Wishlist wi JOIN FETCH wi.product JOIN FETCH wi.customer WHERE wi.customer.id = :customerId")
    List<Wishlist> findByCustomer_Id(Long customerId);
}
