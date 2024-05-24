package com.webshop.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webshop.webshop.entities.Product;
import com.webshop.webshop.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
    }

    public Optional<Product> getProductByProductNameAndCategory(String productName, String category) {
        return productRepository.findByProductNameAndCategory(productName, category);
    }

    public boolean isThereEnoughProduct(Long id, int quantity) {
        Product product = getProductById(id);
        return product.getQuantity() - quantity >= 0;
    }

}