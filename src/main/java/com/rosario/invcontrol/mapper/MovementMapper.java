package com.rosario.invcontrol.mapper;

import com.rosario.invcontrol.dto.MovementRequest;
import com.rosario.invcontrol.model.Product;
import com.rosario.invcontrol.model.StockMovement;
import org.springframework.stereotype.Component;

@Component
public class MovementMapper {
    public StockMovement toEntity(MovementRequest request, Product product){
        StockMovement movement = new StockMovement();
        movement.setProduct(product);
        movement.setType(request.type());
        movement.setQuantity(request.quantity());
        movement.setNote(request.note());
        return movement;
    }
}
