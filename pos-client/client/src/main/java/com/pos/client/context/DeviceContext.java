package com.pos.client.context;

import com.pos.client.devices.PosDevice;
import lombok.Getter;
import lombok.Setter;

public class DeviceContext {

    // Printer
    @Setter
    @Getter
    private static PosDevice printer;
    // Scanner
    @Setter
    @Getter
    private static PosDevice scanner;

}
