package com.minipos.server.controllers;

import com.minipos.server.dto.OrderRequest;
import com.minipos.server.models.Order;
import com.minipos.server.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @PostMapping
    public ResponseEntity<Order> create(@Valid @RequestBody OrderRequest req) {
        Order o = orderService.createOrder(req);
        return ResponseEntity.status(201).body(o);
    }
}
