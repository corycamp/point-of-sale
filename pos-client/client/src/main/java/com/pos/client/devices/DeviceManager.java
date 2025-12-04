package com.pos.client.devices;

import com.fazecast.jSerialComm.SerialPort;

import java.util.ArrayList;
import java.util.List;

public class DeviceManager {

    private final List<PosDevice> devices = new ArrayList<>();

    public DeviceManager() {
        // Initial scan on creation
        scanDevices();
    }

    /**
     * Returns all devices currently known by the system.
     */
    public List<PosDevice> getDevices() {
        return devices;
    }

    /**
     * Scans RS232 and USB devices and replaces the current list.
     */
    public void scanDevices() {
        devices.clear();

        // ----- Scan RS232 / Serial -----
        for (SerialPort port : SerialPort.getCommPorts()) {
            devices.add(new SerialDevice(
                    port.getDescriptivePortName(),
                    "RS232",
                    port.getSystemPortName(),
                    port
            ));
        }

        // ----- Simulated USB devices (can be replaced with real USB/HID code) -----
        devices.add(new UsbDevice("Thermal Printer", "USB", "VID_8866&PID_2040"));
        devices.add(new UsbDevice("Barcode Scanner", "USB", "VID_1234&PID_1020"));
    }

    /**
     * Attempts to connect to the given device.
     */
    public boolean connect(PosDevice device) {
        return device.connect();
    }

    /**
     * Disconnects from the given device.
     */
    public void disconnect(PosDevice device) {
        device.disconnect();
    }
}
