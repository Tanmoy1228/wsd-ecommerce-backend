package com.wsd.ecommerce_app.repository;

import com.wsd.ecommerce_app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsById(Long id);
}
