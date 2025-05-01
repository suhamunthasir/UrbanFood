package com.urbanfood.controller;

import com.urbanfood.model.Customer;
import com.urbanfood.model.LoginRequest;
import com.urbanfood.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/byemail/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable   String email) {
        Customer customer = customerService.getCustomerByEmail(email);  // Use customerService to find by email
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }



    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerService.getCustomerById(id)
                .map(existing -> {
                    existing.setName(updatedCustomer.getName());
                    existing.setEmail(updatedCustomer.getEmail());
                    existing.setAddress(updatedCustomer.getAddress());
                    existing.setPhone(updatedCustomer.getPhone());
                    existing.setPassword(updatedCustomer.getPassword());
                    return customerService.saveCustomer(existing);
                }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Customer customer = customerService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (customer != null) {
            return ResponseEntity.ok(customer); // Return customer details if login is successful
        }
        return ResponseEntity.status(401).body("Invalid credentials"); // Error message for failed login
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customer) {
        Customer registeredCustomer = customerService.register(customer);
        if (registeredCustomer != null) {
            return ResponseEntity.ok(registeredCustomer); // Return registered customer object
        }
        return ResponseEntity.status(400).body("Email already in use"); // Error message if email is already taken
    }
}
