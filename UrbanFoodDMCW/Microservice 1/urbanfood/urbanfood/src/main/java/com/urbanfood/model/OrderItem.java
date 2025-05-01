package com.urbanfood.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique ID for each OrderItem

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;  // The order that this item belongs to

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;  // The product being ordered

    private int quantity;  // Quantity of the product ordered
    private double price;  // Price of the product at the time of the order

    // Default constructor
    public OrderItem() {}

    // Constructor with fields
    public OrderItem(Order order, Product product, int quantity, double price) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
