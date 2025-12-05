package com.minipos.server.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="inventory")
public class Inventory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="product_id", nullable=false, unique=true)
    private Product product;

    @Column(nullable=false)
    private Integer quantity = 0;

    @Column(name="last_updated")
    private LocalDateTime lastUpdated = LocalDateTime.now();

    public Inventory() {}

    public Inventory(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
        this.lastUpdated = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}

