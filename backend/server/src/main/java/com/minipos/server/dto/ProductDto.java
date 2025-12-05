package com.minipos.server.dto;

import java.math.BigDecimal;

public class ProductDto {
    private Long id;
    private String sku;
    private String name;
    private BigDecimal price;
    private Integer reorderLevel;

    public ProductDto() {}

    public ProductDto(Long id, String sku, String name, BigDecimal price, Integer reorderLevel) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.reorderLevel = reorderLevel;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Integer getReorderLevel() { return reorderLevel; }
    public void setReorderLevel(Integer reorderLevel) { this.reorderLevel = reorderLevel; }
}
