package dev.manuel.bluetoothscanner.application.services;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

public class ConnectThread extends Thread {

    private final BluetoothSocket bluetoothSocket;

    @SuppressLint("MissingPermission")
    public ConnectThread(BluetoothDevice device, UUID uuid){
        BluetoothSocket tmp = null;
        try {
            tmp = device.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e){
            Log.d("CONNECT-THREAD", "Could not start listening for RFCOMM");
        }
        bluetoothSocket = tmp;
    }

    @SuppressLint("MissingPermission")
    public boolean connect() {

        try {
            bluetoothSocket.connect();
        } catch(IOException e) {
            Log.d("CONNECT-THREAD","Could not connect: " + e.toString());
            try {
                bluetoothSocket.close();
            } catch(IOException close) {
                Log.d("CONNECT-THREAD", "Could not close connection:" + e.toString());
                return false;
            }
        }
        return true;
    }

    public boolean cancel() {
        try {
            bluetoothSocket.close();
        } catch(IOException e) {
            return false;
        }
        return true;
    }


}
