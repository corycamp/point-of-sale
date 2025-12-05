package com.minipos.server.services;

import com.minipos.server.models.Inventory;
import com.minipos.server.models.Product;
import com.minipos.server.repo.InventoryRepository;
import com.minipos.server.repo.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final InventoryRepository inventoryRepo;

    public ProductService(ProductRepository productRepo, InventoryRepository inventoryRepo) {
        this.productRepo = productRepo;
        this.inventoryRepo = inventoryRepo;
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Product getBySku(String sku) {
        return productRepo.findBySku(sku).orElseThrow(() -> new RuntimeException("Product not found: " + sku));
    }

    @Transactional
    public Product create(Product p) {
        Product saved = productRepo.save(p);
        // create inventory row with zero stock if missing
        Inventory inv = new Inventory(saved, 0);
        inventoryRepo.save(inv);
        return saved;
    }

    @Transactional
    public Product updateStock(String sku, int delta) {
        Product product = getBySku(sku);
        Inventory inventory = inventoryRepo.findByProduct(product)
                .orElseGet(() -> new Inventory(product, 0));
        inventory.setQuantity(inventory.getQuantity() + delta);
        inventory.setLastUpdated(java.time.LocalDateTime.now());
        inventoryRepo.save(inventory);
        return product;
    }
}
