package com.wsd.ecommerce_app.repository;

import com.wsd.ecommerce_app.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findByCustomer_Id(Long customerId);
}
