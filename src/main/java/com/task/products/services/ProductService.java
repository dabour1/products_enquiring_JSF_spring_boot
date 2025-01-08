package com.task.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.products.exceptions.ProductNotFoundException;
import com.task.products.models.Product;
import com.task.products.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public int getProductQuantityByName(String name) throws ProductNotFoundException {

        Optional<Product> product = productRepository.findByName(name);

        if (product.isPresent()) {
            return product.get().getQuantity();
        }

        throw new ProductNotFoundException("Product not found");

    }

}
