package com.urbanfood.repository;

import com.urbanfood.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

// In CustomerRepository class
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);  // Ensure it's querying by email properly
}
