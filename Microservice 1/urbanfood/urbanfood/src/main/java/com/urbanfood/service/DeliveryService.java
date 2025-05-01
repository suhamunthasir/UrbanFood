package com.urbanfood.service;

import com.urbanfood.model.Delivery;
import com.urbanfood.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {
    @Autowired
    private DeliveryRepository deliveryRepository;

    // Get all deliveries
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    // Get a delivery by ID
    public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }

    // Save a new delivery
    public Delivery saveDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public Delivery updateDelivery(Long id, Delivery updatedDelivery) {
        return deliveryRepository.findById(id).map(delivery -> {
            delivery.setDeliveryAddress(updatedDelivery.getDeliveryAddress());
            delivery.setDeliveryStatus(updatedDelivery.getDeliveryStatus());
            delivery.setOrderId(updatedDelivery.getOrderId());
            delivery.setDeliveryDate(updatedDelivery.getDeliveryDate());
            return deliveryRepository.save(delivery);
        }).orElseThrow(() -> new RuntimeException("Delivery not found with id " + id));
    }


    // Delete a delivery
    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
}
