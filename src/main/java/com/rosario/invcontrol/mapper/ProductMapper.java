package com.rosario.invcontrol.mapper;

import com.rosario.invcontrol.dto.ProductRequest;
import com.rosario.invcontrol.dto.ProductResponse;
import com.rosario.invcontrol.model.Category;
import com.rosario.invcontrol.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntity(ProductRequest request, Category category) {
        Product product = new Product();
        product.setName(request.name());
        product.setQuantity(request.quantity());
        product.setPrice(request.price());
        product.setCategory(category);
        return product;
    }

    public ProductResponse toResponse(Product product) {
        Category category = product.getCategory();
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getQuantity(),
                product.getPrice(),
                category != null ? category.getId() : null,
                category != null ? category.getName() : null
        );
    }
}


