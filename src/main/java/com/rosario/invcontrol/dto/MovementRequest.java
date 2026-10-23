package com.rosario.invcontrol.dto;

import com.rosario.invcontrol.model.MovementType;

import java.util.UUID;

public record MovementRequest (
        UUID productId,
        MovementType type,
        Integer quantity,
        String note
){}