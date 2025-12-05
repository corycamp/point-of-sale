package com.minipos.server.controllers;


import com.minipos.server.models.Product;
import com.minipos.server.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) { this.productService = productService; }

    @GetMapping
    public ResponseEntity<List<Product>> all() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Product> bySku(@PathVariable String sku) {
        Product p = productService.getBySku(sku);
        return ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product payload) {
        Product saved = productService.create(payload);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{sku}/stock")
    public ResponseEntity<Product> adjustStock(@PathVariable String sku, @RequestParam int delta) {
        Product updated = productService.updateStock(sku, delta);
        return ResponseEntity.ok(updated);
    }
}
