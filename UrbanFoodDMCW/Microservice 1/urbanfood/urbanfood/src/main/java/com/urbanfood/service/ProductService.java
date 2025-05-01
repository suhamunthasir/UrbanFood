
package com.urbanfood.service;

import com.urbanfood.model.Product;
import com.urbanfood.model.Supplier;
import com.urbanfood.repository.ProductRepository;
import com.urbanfood.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final String uploadDir = "C:/Users/suham/Downloads/ProjectPictures/";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // 🆕 Save new product with image
    public Product saveProduct(String name, String description, double price, int stockQuantity,
                               String category, MultipartFile image, Long supplierId) throws IOException {

        String filename = saveImageToDisk(image);
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Product product = new Product(name, description, price, stockQuantity, category, filename, supplier);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, String name, String description, double price,
                                 int stockQuantity, String category, MultipartFile image,
                                 Long supplierId) throws IOException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        product.setCategory(category);

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        product.setSupplier(supplier);

        if (image != null && !image.isEmpty()) {
            String imageName = image.getOriginalFilename();
            Path imagePath = Paths.get("C:/Users/suham/Downloads/ProjectPictures", imageName);
            Files.write(imagePath, image.getBytes());
            product.setImageUrl(imageName);
        }

        return productRepository.save(product);
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private String saveImageToDisk(MultipartFile image) throws IOException {
        if (image.isEmpty()) return null;

        String filename = image.getOriginalFilename();
        Path path = Paths.get(uploadDir + filename);
        Files.write(path, image.getBytes());
        return filename;
    }
}