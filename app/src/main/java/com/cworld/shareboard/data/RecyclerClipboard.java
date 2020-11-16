package com.cworld.shareboard.data;

public class RecyclerClipboard {
    String board;
    String date;
    String deviceName;
    String userId;
    String deviceType;

    public String getBoard() { return board; }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType= deviceType;
    }
}
