package com.rosario.invcontrol.repository;

import com.rosario.invcontrol.model.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MovementRepository extends JpaRepository<StockMovement, UUID> {
    List<StockMovement> findByProductIdOrderByCreatedAtDesc(UUID productId);
}
