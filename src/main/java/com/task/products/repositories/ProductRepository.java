package com.task.products.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.products.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

}
