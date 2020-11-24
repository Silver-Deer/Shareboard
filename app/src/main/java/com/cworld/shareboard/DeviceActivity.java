package com.cworld.shareboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.cworld.shareboard.Server.RetroFitClient;
import com.cworld.shareboard.data.RetroFitDeviceGet;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        SharedPreferences sharedPreferences = getSharedPreferences("sFile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String token = sharedPreferences.getString("token", "");
        String userId = sharedPreferences.getString("userId", "");
        Log.e("Device", userId);
        Log.e("Device", token);
        String macAddress = getMACAddress("wlan0").replaceAll(":", "");
        Log.e("Device ", macAddress);

        Button logout = findViewById(R.id.logout);
        Button getDevice = findViewById(R.id.activity_device_getDevice);

        getDevice.setOnClickListener(v-> {
            Call<RetroFitDeviceGet> call = RetroFitClient.getInstance().getApi().getDevice(token);
            call.enqueue(new Callback<RetroFitDeviceGet>() {
                @Override
                public void onResponse(Call<RetroFitDeviceGet> call, Response<RetroFitDeviceGet> response) {
                    if(response.code() == 200) {
                        List<RetroFitDeviceGet.Device> deviceList = response.body().getDevice();
                        for(int position = 0; position < deviceList.size(); position++) {
                            Log.e("Device", deviceList.get(position).getDeviceName());
                        }
                    } else {
                        Log.e("Device", String.valueOf(response.code()));
                    }
                }

                @Override
                public void onFailure(Call<RetroFitDeviceGet> call, Throwable t) {
                    Log.e("Device", t.getMessage());
                }
            });
        });

        logout.setOnClickListener(v->{
            Intent intent = new Intent(DeviceActivity.this, LoginActivity.class);
            editor.remove("token");
            editor.commit();

            startActivity(intent);
            finish();
        });

    }

    public static String getMACAddress(String interfaceName) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (interfaceName != null) {
                    if (!intf.getName().equalsIgnoreCase(interfaceName)) continue;
                }
                byte[] mac = intf.getHardwareAddress();
                if (mac==null) return "";
                StringBuilder buf = new StringBuilder();
                for (int idx=0; idx<mac.length; idx++)
                    buf.append(String.format("%02X:", mac[idx]));
                if (buf.length()>0) buf.deleteCharAt(buf.length()-1);
                return buf.toString();
            }
        } catch (Exception ex) { } // for now eat exceptions
        return "";
    }
}