package com.rosario.invcontrol.mapper;

import com.rosario.invcontrol.dto.CategoryRequest;
import com.rosario.invcontrol.dto.CategoryResponse;
import com.rosario.invcontrol.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryRequest request){
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        return category;
    }

    public CategoryResponse toResponse(Category category){
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
