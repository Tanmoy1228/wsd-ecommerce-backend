package com.wsd.ecommerce_app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldReturnTrueWhenCustomerExists() {

        Long customerId = 1L;

        boolean exists = customerRepository.existsById(customerId);

        assertThat(exists).isTrue();
    }

    @Test
    void shouldReturnFalseWhenCustomerDoesNotExist() {

        Long customerId = 999L;

        boolean exists = customerRepository.existsById(customerId);

        assertThat(exists).isFalse();
    }
}