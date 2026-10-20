package com.rosario.invcontrol.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ProductRequest (
    @NotBlank(message = "Name is required!")
    String name,

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    Integer quantity,

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    Double price,

    UUID categoryId
){}
