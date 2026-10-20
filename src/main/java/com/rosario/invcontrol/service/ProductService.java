package com.rosario.invcontrol.service;

import com.rosario.invcontrol.dto.ProductRequest;
import com.rosario.invcontrol.dto.ProductResponse;
import com.rosario.invcontrol.exception.ResourceNotFoundException;
import com.rosario.invcontrol.mapper.ProductMapper;
import com.rosario.invcontrol.model.Category;
import com.rosario.invcontrol.model.Product;
import com.rosario.invcontrol.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final CategoryService service;
    private final ProductMapper mapper;

    public List<ProductResponse> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public ProductResponse findById(UUID id) {
        return mapper.toResponse(getEntity(id));
    }

    public List<Product> getByCategoryId(UUID id){
        return repository.findByCategoryId(id);
    }

    public ProductResponse create(ProductRequest request) {
        Category category = resolveCategory(request.categoryId());
        Product product = mapper.toEntity(request, category);
        return mapper.toResponse(repository.save(product));
    }

    public ProductResponse update(UUID id, ProductRequest request){
        Product product = getEntity(id);
        product.setName(request.name());
        product.setQuantity((request.quantity()));
        product.setPrice(request.price());
        product.setCategory(resolveCategory(request.categoryId()));
        return mapper.toResponse(repository.save(product));
    }

    public void delete(UUID id) {
        getEntity(id);
        repository.deleteById(id);
    }

    public List<ProductResponse> lowStock(){
        return repository.findByQuantityLessThan(5).stream().map(mapper::toResponse).toList();
    }

    private Product getEntity(UUID id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }

    private Category resolveCategory(UUID categoryId){
        return categoryId != null ? service.findById(categoryId) : null;
    }
}
