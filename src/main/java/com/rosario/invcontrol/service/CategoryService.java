package com.rosario.invcontrol.service;

import com.rosario.invcontrol.exception.DuplicateResourceException;
import com.rosario.invcontrol.exception.ResourceInUseException;
import com.rosario.invcontrol.exception.ResourceNotFoundException;
import com.rosario.invcontrol.model.Category;
import com.rosario.invcontrol.repository.CategoryRepository;
import com.rosario.invcontrol.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final ProductRepository productRepository;

    public List<Category> findAll() { return repository.findAll(); }

    public List<Category> search(String search) {return repository.findByNameContainingIgnoreCase(search);}

    public Category findById(UUID id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found " + id));
    }

    public Category save(Category category) {
        if(repository.existsByNameIgnoreCase(category.getName())){
            throw new DuplicateResourceException("Category already exists: " + category.getName());
        }
        return repository.save(category);
    }

    public Category update(UUID id, Category category){
        findById(id);
        if(repository.existsByNameIgnoreCaseAndIdNot(category.getName(), id)){
            throw new DuplicateResourceException("Category already exists :" +  category.getName());
        }
        category.setId(id);
        return repository.save(category);
    }

    public void delete(UUID id) {
        findById(id);
        if(productRepository.existsByCategoryId(id)){
            throw new ResourceInUseException("Category has products and cannot be deleted");
        }
        repository.deleteById(id);
    }
}
