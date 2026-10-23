package com.rosario.invcontrol.service;

import com.rosario.invcontrol.dto.MovementRequest;
import com.rosario.invcontrol.dto.MovementResponse;
import com.rosario.invcontrol.mapper.MovementMapper;
import com.rosario.invcontrol.model.Product;
import com.rosario.invcontrol.model.StockMovement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockMovementService {
    private final ProductService productService;
    private final MovementMapper mapper;
    public MovementResponse create(MovementRequest request){
        Product product = resolveProduct(request.productId());
        StockMovement stockMovement = mapper.toEntity(request, product);

    }

    private Product resolveProduct(UUID productId){
        return productId != null ? productService.getEntity(productId) : null;
    }
}
