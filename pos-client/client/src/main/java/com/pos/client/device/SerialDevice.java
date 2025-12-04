package com.pos.client.device;

import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialDevice {
    private SerialPort port;

    public SerialDevice(String portName) {
        port = new SerialPort(portName);
    }

    public void start() throws SerialPortException {
        port.openPort();
        port.setParams(9600, 8, 1, 0);

        new Thread(this::readLoop).start();
    }

    private void readLoop() {
        try {
            while (true) {
                String msg = port.readString();
                if (msg != null) {
                    System.out.println("Device Says: " + msg);
                }
            }
        } catch (Exception ignored) {}
    }

    public void send(String msg) throws SerialPortException {
        port.writeString(msg);
    }
}
