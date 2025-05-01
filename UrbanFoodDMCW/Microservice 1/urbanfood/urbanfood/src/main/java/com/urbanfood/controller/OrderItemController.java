package com.urbanfood.controller;

import com.urbanfood.model.OrderItem;
import com.urbanfood.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderitems")
@CrossOrigin
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    // Endpoint to add an order item to an order
    @PostMapping
    public ResponseEntity<OrderItem> addOrderItem(@RequestParam Long orderId,
                                                  @RequestParam Long productId,
                                                  @RequestParam int quantity) {
        try {
            // Add the order item and return the created order item with 201 status code
            OrderItem orderItem = orderItemService.addOrderItem(orderId, productId, quantity);
            return new ResponseEntity<>(orderItem, HttpStatus.CREATED);
        } catch (Exception e) {
            // Return 400 if there's an issue (like invalid order/product)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to get all order items
    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    // Endpoint to get a specific order item by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        OrderItem orderItem = orderItemService.getOrderItemById(id);

        if (orderItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 if not found
        }
        return new ResponseEntity<>(orderItem, HttpStatus.OK);  // 200 if found
    }

    // Endpoint to delete an order item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        try {
            orderItemService.deleteOrderItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 for successful delete
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 404 if order item not found
        }
    }
}
