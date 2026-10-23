package com.rosario.invcontrol.controller;

import com.rosario.invcontrol.dto.MovementRequest;
import com.rosario.invcontrol.dto.MovementResponse;
import com.rosario.invcontrol.service.StockMovementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movements")
@RequiredArgsConstructor
public class MovementController {
    private final StockMovementService service;

    @PostMapping
    public ResponseEntity<MovementResponse> register(@Valid @RequestBody MovementRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(request));
    }

    @GetMapping("/product/{productId}")
    public List<MovementResponse> getMovementByProductId(@PathVariable UUID productId){
        return service.getByProductId(productId);
    }

}
