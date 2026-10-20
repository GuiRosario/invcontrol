package com.rosario.invcontrol.dto;

import java.util.UUID;

public record ProductResponse (
        UUID id,
        String name,
        Integer quantity,
        Double price,
        UUID categoryId,
        String categoryName
){}
