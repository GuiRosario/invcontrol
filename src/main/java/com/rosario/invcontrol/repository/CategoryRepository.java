package com.rosario.invcontrol.repository;

import com.rosario.invcontrol.model.Category;
import com.rosario.invcontrol.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository <Category, UUID> {
    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, UUID id);
    List<Category> findByNameContainingIgnoreCase(String search);
}
