package com.urbanfood.controller;

import com.urbanfood.model.Supplier;
import com.urbanfood.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public Optional<Supplier> getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(id);
    }

    @PutMapping("/{id}")
    public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier updatedSupplier) {
        return supplierService.getSupplierById(id)
                .map(existingSupplier -> {
                    existingSupplier.setName(updatedSupplier.getName());
                    existingSupplier.setContact(updatedSupplier.getContact());
                    existingSupplier.setEmail(updatedSupplier.getEmail());
                    existingSupplier.setAddress(updatedSupplier.getAddress());
                    return supplierService.saveSupplier(existingSupplier);
                })
                .orElseThrow(() -> new RuntimeException("Supplier not found with id " + id));
    }

    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return supplierService.saveSupplier(supplier);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
    }
}
