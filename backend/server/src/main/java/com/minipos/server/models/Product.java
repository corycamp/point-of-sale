package com.minipos.server.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String sku;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private BigDecimal price;

    @Column(name="reorder_level")
    private Integer reorderLevel = 0;

    public Product() {}

    public Product(String sku, String name, BigDecimal price, Integer reorderLevel) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.reorderLevel = reorderLevel;
    }

    public Long getId() { return id; }
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Integer getReorderLevel() { return reorderLevel; }
    public void setReorderLevel(Integer reorderLevel) { this.reorderLevel = reorderLevel; }
}

