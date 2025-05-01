package com.urbanfood.service;

import com.urbanfood.model.OrderItem;
import com.urbanfood.model.Order;
import com.urbanfood.model.Product;
import com.urbanfood.repository.OrderItemRepository;
import com.urbanfood.repository.OrderRepository;
import com.urbanfood.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found with ID: " + id));
    }

    public OrderItem addOrderItem(Long orderId, Long productId, int quantity) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        double price = product.getPrice() * quantity;

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setPrice(price);

        // Add the item price to the order total and save the order
        order.setTotalAmount(order.getTotalAmount() + price);
        orderRepository.save(order);

        // Save the new order item
        return orderItemRepository.save(item);
    }

    public void deleteOrderItem(Long id) {
        OrderItem item = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found with ID: " + id));

        Order order = item.getOrder();
        order.setTotalAmount(order.getTotalAmount() - item.getPrice());

        // Recalculate the total amount after the item is deleted
        orderRepository.save(order);

        // Delete the order item
        orderItemRepository.deleteById(id);
    }
}
