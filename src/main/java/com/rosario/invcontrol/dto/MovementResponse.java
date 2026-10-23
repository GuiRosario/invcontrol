package com.rosario.invcontrol.dto;

import com.rosario.invcontrol.model.MovementType;

import java.time.LocalDateTime;
import java.util.UUID;

public record MovementResponse (
        UUID id,
        UUID productId,
        String productName,
        MovementType type,
        Integer quantity,
        LocalDateTime createdAt,
        String note
){}