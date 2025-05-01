package com.urbanfood.controller;

import com.urbanfood.model.Product;
import com.urbanfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }



    // 🆕 Create product with image upload
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Product> createProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam int stockQuantity,
            @RequestParam String category,
            @RequestParam("image") MultipartFile image,
            @RequestParam Long supplierId) throws IOException {

        Product savedProduct = productService.saveProduct(name, description, price, stockQuantity, category, image, supplierId);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // 🆕 Update product with image upload
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double price,
            @RequestParam int stockQuantity,
            @RequestParam String category,
            @RequestParam(value = "image", required = false) MultipartFile image,

            @RequestParam Long supplierId) throws IOException {

        Product updated = productService.updateProduct(id, name, description, price, stockQuantity, category, image, supplierId);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
