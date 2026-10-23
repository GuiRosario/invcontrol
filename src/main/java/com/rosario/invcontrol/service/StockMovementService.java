package com.rosario.invcontrol.service;

import com.rosario.invcontrol.dto.MovementRequest;
import com.rosario.invcontrol.dto.MovementResponse;
import com.rosario.invcontrol.exception.InsufficientStockException;
import com.rosario.invcontrol.mapper.MovementMapper;
import com.rosario.invcontrol.model.MovementType;
import com.rosario.invcontrol.model.Product;
import com.rosario.invcontrol.model.StockMovement;
import com.rosario.invcontrol.repository.MovementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockMovementService {
    private final ProductService productService;
    private final MovementMapper mapper;
    private final MovementRepository repository;

    @Transactional
    public MovementResponse register(MovementRequest request){
        Product product = resolveProduct(request.productId());
        StockMovement stockMovement = mapper.toEntity(request, product);
        if(stockMovement.getType() == MovementType.IN){
            product.setQuantity(product.getQuantity() + stockMovement.getQuantity());
        } else{
            if(stockMovement.getQuantity() > product.getQuantity()){
                throw new InsufficientStockException("There isn't enough stock");
            }
            product.setQuantity(product.getQuantity() - stockMovement.getQuantity());
        }
        return mapper.toResponse(repository.save(stockMovement));
    }

    public List<MovementResponse> getByProductId(UUID id){
        resolveProduct(id);
        return repository.findByProductIdOrderByCreatedAtDesc(id).stream().map(mapper::toResponse).toList();
    }

    private Product resolveProduct(UUID productId){
        return productId != null ? productService.getEntity(productId) : null;
    }
}
