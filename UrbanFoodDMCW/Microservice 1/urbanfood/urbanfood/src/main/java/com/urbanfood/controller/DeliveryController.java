package com.urbanfood.controller;

import com.urbanfood.model.Delivery;
import com.urbanfood.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    @Autowired
    private DeliveryService deliveryService;

    // Get all deliveries
    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    // Get a delivery by ID
    @GetMapping("/{id}")
    public Optional<Delivery> getDeliveryById(@PathVariable Long id) {
        return deliveryService.getDeliveryById(id);
    }

    // Create a new delivery
    @PostMapping
    public Delivery createDelivery(@RequestBody Delivery delivery) {
        return deliveryService.saveDelivery(delivery);
    }

    // Update an existing delivery
    @PutMapping("/{id}")
    public Delivery updateDelivery(@PathVariable Long id, @RequestBody Delivery updatedDelivery) {
        return deliveryService.updateDelivery(id, updatedDelivery);
    }

    // Delete a delivery
    @DeleteMapping("/{id}")
    public void deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
    }
}
