package com.pos.client.devices;

public class UsbDevice extends PosDevice {

    public UsbDevice(String name, String type, String identifier) {
        super(name, type, identifier);
    }

    @Override
    public void send(String receipt) {
        //No Action taken with USB
    }

    @Override
    public boolean connect() {
        // In a real version you'd use USB HID API or libusb
        isConnected = true;
        return true;
    }

    @Override
    public void disconnect() {
        isConnected = false;
    }
}
