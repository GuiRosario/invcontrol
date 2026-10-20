package com.rosario.invcontrol.controller;

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
    public List<Category> list() {return service.findAll();}

    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategory(
            @RequestParam String name
    ) {return ResponseEntity.ok(service.search(name));}

    @GetMapping("/{id}")
    public Category get(@PathVariable UUID id) {return service.findById(id);}


    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody Category category){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(category));
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable UUID id, @Valid @RequestBody Category category){
        return service.update(id,category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
