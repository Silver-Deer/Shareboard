package com.cworld.shareboard.data;

import android.bluetooth.BluetoothClass;

import java.util.List;

public class RetroFitDeviceGet {
    String userId;
    List<Device> device;

    public RetroFitDeviceGet(String userId) {
        this.userId = userId;
    }

    public List<Device> getDevice() {
        return device;
    }

    public class Device {
        int deviceId;
        String deviceName;
        String deviceToken;
        String userId;
        int typeId;
        String typeName;

        public int getDeviceId() {
            return deviceId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public String getUserId() {
            return userId;
        }

        public int getTypeId() {
            return typeId;
        }

        public String getTypeName() {
            return typeName;
        }
    }


}
