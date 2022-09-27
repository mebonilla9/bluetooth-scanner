package dev.manuel.bluetoothscanner.domain.repositories;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

public class ConnectThread extends Thread{

    private BluetoothSocket bluetoothSocket;

    @SuppressLint("MissingPermission")
    public boolean connect(BluetoothDevice device, UUID uuid) {
        BluetoothSocket temp = null;
        try {
            temp = device.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) {
            Log.d("CONNECT-THREAD","Could not create RFCOMM socket:" + e.toString());
            return false;
        }
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
            Log.d("CONNECT-THREAD","Could not close connection:" + e.toString());
            return false;
        }
        return true;
    }

}
