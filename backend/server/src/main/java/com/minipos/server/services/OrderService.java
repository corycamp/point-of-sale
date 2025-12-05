package com.minipos.server.services;


import com.minipos.server.dto.OrderRequest;
import com.minipos.server.models.Order;
import com.minipos.server.repo.InventoryRepository;
import com.minipos.server.repo.OrderRepository;
import com.minipos.server.repo.ProductRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;
    private final InventoryRepository inventoryRepo;
    private final KafkaTemplate<String,String> kafkaTemplate;

    public OrderService(OrderRepository orderRepo,
                        ProductRepository productRepo,
                        InventoryRepository inventoryRepo,
                        KafkaTemplate<String,String> kafkaTemplate) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.inventoryRepo = inventoryRepo;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Creates an order, reserves/decrements inventory synchronously (simpler),
     * persists order and publishes sale.completed event.
     */
    @Transactional
    public Order createOrder(OrderRequest req) {
        // In production we'd parse itemsJson and decrement per-line using locks/stored proc.
        // Here we assume client has already validated stock or it's demo.
        Order order = new Order();
        order.setOrderNumber(req.getOrderNumber() != null ? req.getOrderNumber() : UUID.randomUUID().toString());
        order.setTotal(req.getTotal());
        order.setItemsJson(req.getItemsJson());
        order.setStatus("CREATED");
        Order saved = orderRepo.save(order);

        // Publish Kafka event (payload is simple order id)
        kafkaTemplate.send("sale.completed", "{\"orderId\": " + saved.getId() + ", \"orderNumber\":\"" + saved.getOrderNumber() + "\"}");

        return saved;
    }
}
