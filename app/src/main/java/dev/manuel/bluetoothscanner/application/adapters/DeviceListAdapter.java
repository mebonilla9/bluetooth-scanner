package dev.manuel.bluetoothscanner.application.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import dev.manuel.bluetoothscanner.R;
import dev.manuel.bluetoothscanner.domain.entities.DeviceItem;

public class DeviceListAdapter extends ArrayAdapter<DeviceItem> {

    private Context context;
    private BluetoothAdapter bluetoothAdapter;

    public DeviceListAdapter(Context context, List items, BluetoothAdapter adapter) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.bluetoothAdapter = adapter;
        this.context = context;
    }

    private class ViewHolder {
        TextView txtTitle;
    }

    @SuppressLint("ViewHolder")
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        View line = null;
        DeviceItem item = getItem(position);
        final String name = item.getName();
        TextView macAddress = null;
        View viewToUse = null;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        viewToUse = inflater.inflate(R.layout.device_list_item, null);
        viewHolder = new ViewHolder();
        viewHolder.txtTitle = viewToUse.findViewById(R.id.txtTitle);
        viewToUse.setTag(viewHolder);

        macAddress = viewToUse.findViewById(R.id.txtMacAddress);
        line = viewToUse.findViewById(R.id.line);
        viewHolder.txtTitle.setText(item.getName());
        macAddress.setText(item.getAddress());

        if ( item.getName().toString() == "No Devices") {
            macAddress.setVisibility(View.INVISIBLE);
            line.setVisibility(View.INVISIBLE);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                    ((int) RelativeLayout.LayoutParams.WRAP_CONTENT, (int) RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            viewHolder.txtTitle.setLayoutParams(params);
        }

        return viewToUse;



    }
}
