package com.pos.client.devices;

import com.fazecast.jSerialComm.SerialPort;

public class SerialDevice extends PosDevice {

    private final SerialPort port;

    public SerialDevice(String name, String type, String identifier, SerialPort port) {
        super(name, type, identifier);
        this.port = port;
    }

    @Override
    public void send(String data) {
        if (!port.isOpen()) {
            port.openPort();
        }

        byte[] bytes = data.getBytes();
        port.writeBytes(bytes, bytes.length);
    }

    @Override
    public boolean connect() {
        port.setBaudRate(9600);
        isConnected = port.openPort();
        return isConnected;
    }

    @Override
    public void disconnect() {
        if (isConnected) {
            port.closePort();
            isConnected = false;
        }
    }
}
