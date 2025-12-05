package com.minipos.server.controllers;

import com.minipos.server.dto.DeviceHelpRequest;
import com.minipos.server.dto.ForecastResponse;
import com.minipos.server.models.DeviceLog;
import com.minipos.server.repo.DeviceLogRepository;
import com.minipos.server.services.ai.DeviceDiagnosticService;
import com.minipos.server.services.ai.InventoryForecastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final InventoryForecastService forecastService;
    private final DeviceDiagnosticService diagnosticService;
    private final DeviceLogRepository deviceLogRepository;

    public AiController(InventoryForecastService forecastService,
                        DeviceDiagnosticService diagnosticService,
                        DeviceLogRepository deviceLogRepository) {
        this.forecastService = forecastService;
        this.diagnosticService = diagnosticService;
        this.deviceLogRepository = deviceLogRepository;
    }

    @GetMapping("/forecast")
    public ResponseEntity<ForecastResponse> forecast(@RequestParam String sku) {
        ForecastResponse resp = forecastService.forecastForSku(sku);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/device-help")
    public ResponseEntity<String> deviceHelp(@RequestBody DeviceHelpRequest req) {
        if (req.getDeviceLogId() != null) {
            return ResponseEntity.ok(diagnosticService.analyzeDeviceLogs(req.getDeviceLogId()));
        } else if (req.getMessage() != null) {
            // simple heuristic analysis for immediate messages
            // create a temporary DeviceLog for traceability
            DeviceLog temp = new DeviceLog(req.getDeviceId(), "INFO", req.getMessage());
            deviceLogRepository.save(temp);
            return ResponseEntity.ok(diagnosticService.analyzeDeviceLogs(temp.getId()));
        } else {
            return ResponseEntity.badRequest().body("Provide deviceLogId or message");
        }
    }
}
