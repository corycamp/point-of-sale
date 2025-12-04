package com.pos.client.devices;

public abstract class PosDevice {

    protected final String name;
    protected final String type;
    protected final String identifier;

    protected boolean isConnected = false;

    public PosDevice(String name, String type, String identifier) {
        this.name = name;
        this.type = type;
        this.identifier = identifier;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getIdentifier() { return identifier; }
    public boolean isConnected() { return isConnected; }

    public abstract void send(String receipt);
    public abstract boolean connect();
    public abstract void disconnect();
}
