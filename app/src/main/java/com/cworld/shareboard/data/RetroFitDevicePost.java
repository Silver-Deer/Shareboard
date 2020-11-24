package com.cworld.shareboard.data;

public class RetroFitDevicePost {
    String deviceName;
    String userId;
    String typeName;

    int result;
    int deviceId;

    public RetroFitDevicePost(String deviceName, String userId, String typeName) {
        this.deviceName = deviceName;
        this.userId = userId;
        this.typeName = typeName;
    }

    public int getResult() {
        return result;
    }

    public int getDeviceId() {
        return deviceId;
    }
}
