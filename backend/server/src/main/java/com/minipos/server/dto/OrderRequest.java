package com.minipos.server.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * Simplified DTO for creating orders.
 * itemsJson contains the cart items as JSON on the client side.
 */
public class OrderRequest {

    @NotNull
    private String orderNumber;

    @NotNull
    private BigDecimal total;

    @NotNull
    private String itemsJson;

    public OrderRequest() {}

    public OrderRequest(String orderNumber, BigDecimal total, String itemsJson) {
        this.orderNumber = orderNumber;
        this.total = total;
        this.itemsJson = itemsJson;
    }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getItemsJson() { return itemsJson; }
    public void setItemsJson(String itemsJson) { this.itemsJson = itemsJson; }
}
