package deliverymen.deliverymen.repository;

import deliverymen.deliverymen.model.DeliveryMan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeliveryManRepository {

    private final JdbcTemplate jdbcTemplate;

    public DeliveryManRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addDeliveryMan(String name, String email, String phone, String vehicleNumber) {
        String sql = "CALL add_delivery_man(?, ?, ?, ?)";
        jdbcTemplate.update(sql, name, email, phone, vehicleNumber);
    }

    // Get all delivery men
    public List<DeliveryMan> getAllDeliveryMen() {
        String sql = "SELECT * FROM delivery_men"; 
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            DeliveryMan deliveryMan = new DeliveryMan();
            deliveryMan.setDeliveryManId(rs.getLong("delivery_man_id"));
            deliveryMan.setName(rs.getString("name"));
            deliveryMan.setEmail(rs.getString("email"));
            deliveryMan.setPhone(rs.getString("phone"));
            deliveryMan.setVehicleNumber(rs.getString("vehicle_number"));
            deliveryMan.setStatus(rs.getString("status"));
            deliveryMan.setLastUpdated(rs.getTimestamp("last_updated"));
            return deliveryMan;
        });
    }

    // Get delivery man by ID
    public DeliveryMan getDeliveryManById(Long id) {
        String sql = "SELECT * FROM delivery_men WHERE delivery_man_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            DeliveryMan deliveryMan = new DeliveryMan();
            deliveryMan.setDeliveryManId(rs.getLong("delivery_man_id"));
            deliveryMan.setName(rs.getString("name"));
            deliveryMan.setEmail(rs.getString("email"));
            deliveryMan.setPhone(rs.getString("phone"));
            deliveryMan.setVehicleNumber(rs.getString("vehicle_number"));
            deliveryMan.setStatus(rs.getString("status"));
            deliveryMan.setLastUpdated(rs.getTimestamp("last_updated"));
            return deliveryMan;
        });
    }
}
