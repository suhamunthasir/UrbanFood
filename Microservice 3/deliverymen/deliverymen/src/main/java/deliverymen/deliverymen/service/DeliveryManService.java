package deliverymen.deliverymen.service;

import deliverymen.deliverymen.model.DeliveryMan;
import deliverymen.deliverymen.repository.DeliveryManRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryManService {

    private final DeliveryManRepository deliveryManRepository;

    public DeliveryManService(DeliveryManRepository deliveryManRepository) {
        this.deliveryManRepository = deliveryManRepository;
    }

    public void registerDeliveryMan(String name, String email, String phone, String vehicleNumber) {
        deliveryManRepository.addDeliveryMan(name, email, phone, vehicleNumber);
    }

    // Get all delivery men
    public List<DeliveryMan> getAllDeliveryMen() {
        return deliveryManRepository.getAllDeliveryMen();
    }

    // Get delivery man by ID
    public DeliveryMan getDeliveryManById(Long id) {
        return deliveryManRepository.getDeliveryManById(id);
    }
}
