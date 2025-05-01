package com.urbanfood.service;

import com.urbanfood.model.Payment;
import com.urbanfood.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // Save a new payment
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    // Update the payment status based on orderId
    public boolean updatePaymentStatusByOrderId(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId);
        if (payment != null) {
            payment.setPaymentStatus("Paid");  // Update the payment status
            paymentRepository.save(payment);   // Save updated payment status
            return true;
        }
        return false;  // Payment not found
    }

    // Update an existing payment
    public Payment updatePayment(Long paymentId, Payment updatedPayment) {
        // Check if payment exists, otherwise throw exception
        Optional<Payment> existingPayment = paymentRepository.findById(paymentId);
        if (!existingPayment.isPresent()) {
            throw new RuntimeException("Payment not found with id " + paymentId);
        }

        // Update fields
        Payment payment = existingPayment.get();
        payment.setTotalAmount(updatedPayment.getTotalAmount());
        payment.setPaymentDate(updatedPayment.getPaymentDate());
        payment.setPaymentType(updatedPayment.getPaymentType());
        payment.setPaymentStatus(updatedPayment.getPaymentStatus());
        payment.setCustomerId(updatedPayment.getCustomerId());  // Assuming you're just storing ID
        payment.setOrderId(updatedPayment.getOrderId());        // Assuming you're just storing ID

        return paymentRepository.save(payment);
    }

    // Get all payments
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    // Get payment by ID
    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + paymentId));
    }
}
