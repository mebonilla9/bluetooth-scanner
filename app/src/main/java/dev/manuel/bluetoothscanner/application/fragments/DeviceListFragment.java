package dev.manuel.bluetoothscanner.application.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import dev.manuel.bluetoothscanner.application.events.OnFragmentInteractionListener;
import dev.manuel.bluetoothscanner.domain.entities.DeviceItem;

public class DeviceListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ArrayList<DeviceItem> deviceItems;
    private OnFragmentInteractionListener listener;
    private static BluetoothAdapter bluetoothAdapter;

    private AbsListView listView;
    private ArrayAdapter<DeviceItem> adapter;

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                Log.d("DEVICE LIST", "Bluetooth device found\n");
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                @SuppressLint("MissingPermission") DeviceItem deviceItem = new DeviceItem(
                        device.getName(), device.getAddress(), "false"
                );
            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
