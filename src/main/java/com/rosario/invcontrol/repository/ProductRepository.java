package com.rosario.invcontrol.repository;

import com.rosario.invcontrol.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByQuantityLessThan(Integer quantity);
    List<Product> findByCategoryId(UUID categoryId);
    boolean existsByCategoryId(UUID categoryId);
}
