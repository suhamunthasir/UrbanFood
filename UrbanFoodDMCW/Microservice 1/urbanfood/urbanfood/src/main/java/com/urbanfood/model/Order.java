package com.urbanfood.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long id;

    private LocalDate orderDate;
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Default constructor
    public Order() {
        this.orderDate = LocalDate.now(); // Automatically set the order date to today
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    // Getters and Setters
    public Long getOrderId() { return orderId; }

    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public LocalDate getOrderDate() { return orderDate; }

    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }

    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) { this.customer = customer; }
}
