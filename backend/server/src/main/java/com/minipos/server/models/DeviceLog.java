package com.minipos.server.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="device_logs")
public class DeviceLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;
    private String level;
    @Column(length=2000)
    private String message;
    private LocalDateTime createdAt = LocalDateTime.now();

    public DeviceLog() {}

    public DeviceLog(String deviceId, String level, String message) {
        this.deviceId = deviceId;
        this.level = level;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
