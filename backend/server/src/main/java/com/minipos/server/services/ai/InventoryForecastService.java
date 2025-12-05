package com.minipos.server.services.ai;

import com.minipos.server.dto.ForecastResponse;
import com.minipos.server.models.Inventory;
import com.minipos.server.models.Product;
import com.minipos.server.repo.InventoryRepository;
import com.minipos.server.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Simple average-based forecast for demo purposes.
 */
@Service
public class InventoryForecastService {

    private final InventoryRepository inventoryRepo;
    private final ProductRepository productRepo;

    public InventoryForecastService(InventoryRepository inventoryRepo, ProductRepository productRepo) {
        this.inventoryRepo = inventoryRepo;
        this.productRepo = productRepo;
    }

    /**
     * For demo: compute average daily sales from a fake data source.
     * In a real app, use historical sales data. Here we simulate with random small numbers.
     */
    public ForecastResponse forecastForSku(String sku) {
        Product p = productRepo.findBySku(sku).orElseThrow(() -> new IllegalArgumentException("SKU not found"));
        Inventory inv = inventoryRepo.findByProduct(p).orElse(new Inventory(p, 0));

        // simulate average daily sales using a small deterministic function for demo
        double avgDaily = Math.max(0.1, (p.getSku().hashCode() % 5) + 1); // deterministic pseudo-random
        int daysRemaining = (int)Math.ceil(inv.getQuantity() / avgDaily);
        if (avgDaily == 0) daysRemaining = Integer.MAX_VALUE;

        int suggestedReorder = Math.max(0, (int)Math.ceil(avgDaily * 14) - inv.getQuantity());

        return new ForecastResponse(sku, avgDaily, daysRemaining, suggestedReorder);
    }
}
