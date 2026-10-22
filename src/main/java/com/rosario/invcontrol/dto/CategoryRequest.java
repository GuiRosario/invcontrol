package com.rosario.invcontrol.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
    @NotBlank(message = "name is required")
    @Size(max = 50, message = "name must not exceed 50 characters")
    String name,
    @Size(max = 250, message = "Description must not exceed 250 characters")
    String description
){}
