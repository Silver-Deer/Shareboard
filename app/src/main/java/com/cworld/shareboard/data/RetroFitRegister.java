package com.cworld.shareboard.data;

import com.google.gson.annotations.SerializedName;

public class RetroFitRegister {
    @SerializedName("userId")
    String userId;
    @SerializedName("password")
    String password;

    String result;

    public RetroFitRegister(String id, String pw) {
        this.userId = id;
        this.password = pw;
    }

    public String getId() {
        return userId;
    }

    public String getPw() {
        return password;
    }

    public String getResult() { return result; }
}
