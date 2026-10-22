package com.rosario.invcontrol.controller;

import com.rosario.invcontrol.dto.CategoryRequest;
import com.rosario.invcontrol.dto.CategoryResponse;
import com.rosario.invcontrol.dto.ProductResponse;
import com.rosario.invcontrol.model.Category;
import com.rosario.invcontrol.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public List<CategoryResponse> list() {return service.findAll();}

    @GetMapping("/search")
    public ResponseEntity<List<CategoryResponse>> searchCategory(
            @RequestParam String name
    ) {return ResponseEntity.ok(service.search(name));}

    @GetMapping("/{id}")
    public CategoryResponse get(@PathVariable UUID id) {return service.findById(id);}


    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable UUID id, @Valid @RequestBody CategoryRequest request){
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
