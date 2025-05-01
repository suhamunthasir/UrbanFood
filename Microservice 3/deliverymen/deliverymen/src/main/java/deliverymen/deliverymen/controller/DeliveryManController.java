package deliverymen.deliverymen.controller;

import deliverymen.deliverymen.model.DeliveryMan;
import deliverymen.deliverymen.service.DeliveryManService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliverymen")
@CrossOrigin(origins = "*")
public class DeliveryManController {

    private final DeliveryManService deliveryManService;

    public DeliveryManController(DeliveryManService deliveryManService) {
        this.deliveryManService = deliveryManService;
    }

    @PostMapping("/register")
    public String registerDeliveryMan(@RequestBody DeliveryMan deliveryMan) {
        deliveryManService.registerDeliveryMan(
                deliveryMan.getName(),
                deliveryMan.getEmail(),
                deliveryMan.getPhone(),
                deliveryMan.getVehicleNumber()
        );
        return "Delivery man registered successfully!";
    }

    // GET all delivery men
    @GetMapping
    public List<DeliveryMan> getAllDeliveryMen() {
        return deliveryManService.getAllDeliveryMen();
    }

    // GET a specific delivery man by ID
    @GetMapping("/{id}")
    public DeliveryMan getDeliveryManById(@PathVariable Long id) {
        return deliveryManService.getDeliveryManById(id);
    }
}
