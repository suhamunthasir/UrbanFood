package com.urbanfood.controller;

import com.urbanfood.model.Payment;
import com.urbanfood.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment savePayment(@RequestBody Payment payment) {
        return paymentService.savePayment(payment);
    }

    // PUT: Update an existing payment
    @PutMapping("/{paymentId}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long paymentId, @RequestBody Payment payment) {
        try {
            // Update the payment with the given paymentId
            Payment updatedPayment = paymentService.updatePayment(paymentId, payment);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPayment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Payment not found for update
        }
    }

    // PUT: Update payment status by Order ID
    @PutMapping("/order/{orderId}")
    public ResponseEntity<String> updatePaymentStatus(@PathVariable Long orderId) {
        try {
            // Use PaymentService to update the payment status by orderId
            boolean isUpdated = paymentService.updatePaymentStatusByOrderId(orderId);
            if (isUpdated) {
                return ResponseEntity.ok("Payment status updated to Paid");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found for the given Order ID");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating payment status");
        }
    }

    // GET: Retrieve all payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // GET: Retrieve a specific payment by ID
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentId) {
        try {
            Payment payment = paymentService.getPaymentById(paymentId);
            return ResponseEntity.status(HttpStatus.OK).body(payment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Payment not found
        }
    }
}
