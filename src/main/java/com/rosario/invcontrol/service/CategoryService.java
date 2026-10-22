package com.rosario.invcontrol.service;

import com.rosario.invcontrol.dto.CategoryRequest;
import com.rosario.invcontrol.dto.CategoryResponse;
import com.rosario.invcontrol.dto.ProductResponse;
import com.rosario.invcontrol.exception.DuplicateResourceException;
import com.rosario.invcontrol.exception.ResourceInUseException;
import com.rosario.invcontrol.exception.ResourceNotFoundException;
import com.rosario.invcontrol.mapper.CategoryMapper;
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
    private final CategoryMapper mapper;

    public List<CategoryResponse> findAll() { return repository.findAll().stream().map(mapper::toResponse).toList(); }

    public List<CategoryResponse> search(String search) {return repository.findByNameContainingIgnoreCase(search).stream().map(mapper::toResponse).toList();}

    public CategoryResponse findById(UUID id){
        return mapper.toResponse(getEntity(id));
    }

    public CategoryResponse create(CategoryRequest request) {
        Category category = mapper.toEntity(request);
        return mapper.toResponse(repository.save(category));
    }

    public CategoryResponse update(UUID id, CategoryRequest request){
        Category category = getEntity(id);
        if(repository.existsByNameIgnoreCaseAndIdNot(request.name(), id)){
            throw new DuplicateResourceException("Category already exists: " +  request.name());
        }
        category.setName(request.name());
        category.setDescription(request.description());
        return mapper.toResponse(repository.save(category));
    }

    public void delete(UUID id) {
        findById(id);
        if(productRepository.existsByCategoryId(id)){
            throw new ResourceInUseException("Category has products and cannot be deleted");
        }
        repository.deleteById(id);
    }

    public Category getEntity(UUID id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found " + id));
    }
}
