package com.urbanfood.service;

import com.urbanfood.model.Order;
import com.urbanfood.model.Customer;
import com.urbanfood.repository.OrderRepository;
import com.urbanfood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Modify placeOrder to accept the entire Order object
    public Order placeOrder(Order order) {
        // Ensure customer exists and is associated with the order
        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer ID"));

        // Set the customer and save the order
        order.setCustomer(customer);
        return orderRepository.save(order);
    }

    public Order createOrder(Order order) {
        // Save the order to the database and return the saved order (which will include the generated id)
        return orderRepository.save(order);
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public boolean deleteOrder(Long id) {
        // Check if the order exists
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);  // Delete the order
            return true;  // Return true if deletion is successful
        } else {
            return false;  // Return false if the order doesn't exist
        }
    }
}
