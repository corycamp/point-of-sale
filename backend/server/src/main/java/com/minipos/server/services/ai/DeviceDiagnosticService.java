package com.minipos.server.services.ai;

import com.minipos.server.models.DeviceLog;
import com.minipos.server.repo.DeviceLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Local stub that analyzes device logs and returns simple suggestions.
 * Does not call external AI in this demo; in production you would call an LLM or ML model.
 */
@Service
public class DeviceDiagnosticService {

    private final DeviceLogRepository deviceLogRepository;

    public DeviceDiagnosticService(DeviceLogRepository deviceLogRepository) {
        this.deviceLogRepository = deviceLogRepository;
    }

    public String analyzeDeviceLogs(Long deviceLogId) {
        DeviceLog log = deviceLogRepository.findById(deviceLogId).orElse(null);
        if (log == null) return "No logs found for id " + deviceLogId;

        String message = log.getMessage().toLowerCase();
        if (message.contains("timeout")) {
            return "Device appears to be timing out. Suggest checking cable, restarting device, and verifying port settings (baud, parity).";
        } else if (message.contains("no response") || message.contains("not found")) {
            return "Device not responding. Ensure device is powered and connected. Re-scan ports in Device Manager.";
        } else if (message.contains("paper")) {
            return "Printer indicates paper issue. Check paper roll and paper sensor.";
        } else {
            return "Unable to determine root cause from a single log. Check recent device logs and device status. If repeated, gather full logs and contact vendor.";
        }
    }
}
