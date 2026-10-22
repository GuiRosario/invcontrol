package com.rosario.invcontrol.controller;

import com.rosario.invcontrol.dto.ProductRequest;
import com.rosario.invcontrol.dto.ProductResponse;
import com.rosario.invcontrol.model.Product;
import com.rosario.invcontrol.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public List<ProductResponse> list() {return service.findAll();}

    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable UUID id) {return service.findById(id);}

    @GetMapping("/low-stock")
    public List<ProductResponse> lowStock() {return service.lowStock();}

    @GetMapping("/category/{categoryId}")
    public List<ProductResponse> getByCategoryId(@PathVariable UUID categoryId){
        return service.getByCategoryId(categoryId);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable UUID id,@Valid @RequestBody ProductRequest request){
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}