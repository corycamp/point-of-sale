package com.minipos.server.repo;

import com.minipos.server.models.DeviceLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceLogRepository extends JpaRepository<DeviceLog, Long> {
}

