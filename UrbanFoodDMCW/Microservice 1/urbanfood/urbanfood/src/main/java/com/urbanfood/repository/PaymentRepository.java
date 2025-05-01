package com.urbanfood.repository;

import com.urbanfood.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Custom query method to find Payment by orderId
    Payment findByOrderId(Long orderId);
}
