package com.rosario.invcontrol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;
   @NotBlank(message = "name is required")
   @Size(max = 50, message = "name must not exceed 50 characters")
   @Column(unique = true, nullable = false,length = 50)
   private String name;
   @Size(max = 250, message = "Description must not exceed 250 characters")
   private String description;
}
