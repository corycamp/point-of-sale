package com.minipos.server.dto;

public class DeviceHelpRequest {
    private Long deviceLogId;
    private String deviceId;
    private String message;

    public DeviceHelpRequest() {}

    public Long getDeviceLogId() { return deviceLogId; }
    public void setDeviceLogId(Long deviceLogId) { this.deviceLogId = deviceLogId; }
    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
