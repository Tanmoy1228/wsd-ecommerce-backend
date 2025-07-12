package com.wsd.ecommerce_app.repository;

import com.wsd.ecommerce_app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Set<Long> existingCustomers = Set.of(1L, 2L, 3L);

    boolean existsById(Long customerId);
}
