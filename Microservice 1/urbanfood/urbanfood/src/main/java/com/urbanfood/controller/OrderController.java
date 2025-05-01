package com.urbanfood.controller;

import com.urbanfood.model.Order;
import com.urbanfood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Endpoint to place an order (Accepts entire Order object as JSON)
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        // Save order and return the order object with ID
        Order savedOrder = orderService.placeOrder(order);

        // Log for debugging
        System.out.println("Saved Order ID: " + savedOrder.getId());

        // Return the saved order with generated ID as ResponseEntity
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);  // 201 HTTP status code
    }

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if order not found
        }
        return new ResponseEntity<>(order, HttpStatus.OK);  // Return the order if found
    }

    // Delete order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean isDeleted = orderService.deleteOrder(id);

        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 HTTP status code for successful delete
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if order not found
        }
    }
}