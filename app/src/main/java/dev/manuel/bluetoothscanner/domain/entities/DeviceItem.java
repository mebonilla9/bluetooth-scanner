package dev.manuel.bluetoothscanner.domain.entities;

public class DeviceItem {

    private String name;
    private final String address;
    private final boolean connected;

    public DeviceItem(String name, String address, String connected){
        this.name = name;
        this.address = address;
        this.connected = connected.equals("true");
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setName(String name) {
        this.name = name;
    }
}
