package com.urbanfood.controller;

import com.urbanfood.model.Order;
import com.urbanfood.model.OrderItem;
import java.util.List;

public class OrderRequest {
    private Order order;
    private List<OrderItem> orderItems;

    // Getters and Setters
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
