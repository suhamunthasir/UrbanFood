package com.urbanfood.model;

import java.time.LocalDate;

import jakarta.persistence.*;
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    private double totalAmount;

    private LocalDate paymentDate;

    private String paymentType;

    private String paymentStatus;

    private Long customerId; // Simple field for customer ID
    private Long orderId;    // Simple field for order ID

    // Constructors, getters, and setters
    public Payment() {}

    public Payment(double totalAmount, LocalDate paymentDate, String paymentType, String paymentStatus, Long customerId, Long orderId) {
        this.totalAmount = totalAmount;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
        this.customerId = customerId;
        this.orderId = orderId;
    }

    // Getters and setters for all fields
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
