package com.minipos.server.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Simple listeners for demo. In a real setup these would parse messages and perform actions.
 */
@Service
public class KafkaListeners {

    @KafkaListener(topics = "sale.completed", groupId = "minipos-group")
    public void onSaleCompleted(String message) {
        System.out.println("[KafkaListener] sale.completed: " + message);
        // e.g., trigger analytics, update aggregated reports
    }

    @KafkaListener(topics = "inventory.updated", groupId = "minipos-group")
    public void onInventoryUpdated(String message) {
        System.out.println("[KafkaListener] inventory.updated: " + message);
    }

    @KafkaListener(topics = "device.events", groupId = "minipos-group")
    public void onDeviceEvent(String message) {
        System.out.println("[KafkaListener] device.events: " + message);
    }
}
