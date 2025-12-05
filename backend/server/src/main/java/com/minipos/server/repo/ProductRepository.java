package com.minipos.server.repo;

import com.minipos.server.models.Inventory;
import com.minipos.server.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
}

