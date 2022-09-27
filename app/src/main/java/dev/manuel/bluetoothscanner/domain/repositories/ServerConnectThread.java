package dev.manuel.bluetoothscanner.domain.repositories;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

public class ServerConnectThread extends Thread {

    private BluetoothSocket bluetoothSocket;

    public ServerConnectThread() {
    }

    @SuppressLint("MissingPermission")
    public void acceptConnect(BluetoothAdapter bluetoothAdapter, UUID uuid) {
        BluetoothServerSocket temp = null;
        try {
            temp = bluetoothAdapter.listenUsingRfcommWithServiceRecord("Service_Name", uuid);
        } catch (IOException e) {
            Log.d("SERVER-CONNECT", "Could not get a BluetoothServerSocket:" + e.toString());
        }
        while (true) {
            try {
                bluetoothSocket = temp.accept();
            } catch (IOException e) {
                Log.d("SERVER-CONNECT", "Could not accept an incoming connection.");
                break;
            }
            if (bluetoothSocket != null) {
                try {
                    temp.close();
                } catch (IOException e) {
                    Log.d("SERVER-CONNECT", "Could not close ServerSocket:" + e.toString());
                }
                break;
            }
        }
    }

    public void closeConnect() {
        try {
            bluetoothSocket.close();
        } catch (IOException e) {
            Log.d("SERVER-CONNECT", "Could not close connection:" + e.toString());
        }
    }
}
