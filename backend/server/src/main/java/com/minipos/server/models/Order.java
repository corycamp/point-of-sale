package com.minipos.server.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_number", unique=true)
    private String orderNumber;

    @Column(nullable=false)
    private BigDecimal total;

    // For simplicity, store items as JSON string
    @Column(length=4000)
    private String itemsJson;

    @Column(name="status")
    private String status;

    @Column(name="created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public Order() {}

    public Order(String orderNumber, BigDecimal total, String itemsJson, String status) {
        this.orderNumber = orderNumber;
        this.total = total;
        this.itemsJson = itemsJson;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getItemsJson() { return itemsJson; }
    public void setItemsJson(String itemsJson) { this.itemsJson = itemsJson; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
