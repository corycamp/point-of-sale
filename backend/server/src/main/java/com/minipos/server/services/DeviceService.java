package com.minipos.server.services;

import com.minipos.server.models.DeviceLog;
import com.minipos.server.repo.DeviceLogRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private final DeviceLogRepository deviceLogRepository;
    private final KafkaTemplate<String,String> kafkaTemplate;

    public DeviceService(DeviceLogRepository deviceLogRepository, KafkaTemplate<String,String> kafkaTemplate) {
        this.deviceLogRepository = deviceLogRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public DeviceLog logDeviceEvent(String deviceId, String level, String message) {
        DeviceLog log = new DeviceLog(deviceId, level, message);
        DeviceLog saved = deviceLogRepository.save(log);
        // publish to Kafka for consumers (e.g., diagnostic service)
        String payload = "{\"deviceId\":\""+deviceId+"\",\"level\":\""+level+"\",\"message\":\""+message+"\",\"id\":"+saved.getId()+"}";
        kafkaTemplate.send("device.events", payload);
        return saved;
    }
}
