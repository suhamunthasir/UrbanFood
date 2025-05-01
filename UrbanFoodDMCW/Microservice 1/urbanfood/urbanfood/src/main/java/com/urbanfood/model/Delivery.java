package com.urbanfood.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;
    private String deliveryAddress;
    private String deliveryStatus;
    private Long orderId;
    private LocalDate deliveryDate;

    // Constructors, Getters, Setters, toString()
    public Delivery() {}

    public Delivery(String deliveryAddress, String deliveryStatus, Long orderId, LocalDate deliveryDate) {
        this.deliveryAddress = deliveryAddress;
        this.deliveryStatus = deliveryStatus;
        this.orderId = orderId;
        this.deliveryDate = deliveryDate;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Long getOrderId() {  // This getter should be here
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId=" + deliveryId +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                ", orderId=" + orderId +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
