package com.urbanfood.service;

import com.urbanfood.model.Customer;
import com.urbanfood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    // Save customer (Create or update)
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Delete customer
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    // Login method
    public Customer login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer; // Authentication successful
        }
        return null; // Authentication failed
    }

    // Register method
    public Customer register(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()) != null) {
            return null; // Email already in use
        }
        return customerRepository.save(customer); // Save new customer
    }
}
