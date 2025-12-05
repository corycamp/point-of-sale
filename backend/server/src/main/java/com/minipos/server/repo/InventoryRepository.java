package com.minipos.server.repo;

import com.minipos.server.models.Inventory;
import com.minipos.server.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProduct(Product product);
    Optional<Inventory> findByProduct_Id(Long productId);
}
