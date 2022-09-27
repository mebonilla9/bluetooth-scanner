package dev.manuel.bluetoothscanner.domain.repositories;

import android.bluetooth.BluetoothSocket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ManageConnectThread extends Thread{

    public ManageConnectThread() {
    }

    public void sendData(BluetoothSocket socket, int data) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(4);
        baos.write(data);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(baos.toByteArray());
        outputStream.flush();
        outputStream.close();
    }

    public int receiveData(BluetoothSocket socket) throws IOException {
        byte[] buffer = new byte[4];
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        InputStream inputStream = socket.getInputStream();
        inputStream.read(buffer);
        return bais.read();
    }
}
