package com.wsd.ecommerce_app.repository;

import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class CustomerRepository {

    private final Set<Long> existingCustomers = Set.of(1L, 2L, 3L);

    public boolean existsById(Long customerId) {
        return existingCustomers.contains(customerId);
    }
}
