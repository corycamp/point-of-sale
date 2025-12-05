package com.minipos.server.controllers;


import com.minipos.server.models.DeviceLog;
import com.minipos.server.services.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) { this.deviceService = deviceService; }

    @PostMapping("/events")
    public ResponseEntity<DeviceLog> postEvent(@RequestParam String deviceId,
                                               @RequestParam(defaultValue = "INFO") String level,
                                               @RequestBody String message) {
        DeviceLog saved = deviceService.logDeviceEvent(deviceId, level, message);
        return ResponseEntity.ok(saved);
    }
}
