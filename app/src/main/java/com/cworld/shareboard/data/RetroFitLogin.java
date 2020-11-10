package com.cworld.shareboard.data;

import com.google.gson.annotations.SerializedName;

public class RetroFitLogin {
    @SerializedName("userId")
    String userId;
    @SerializedName("password")
    String password;

    @SerializedName("token")
    String token;
    String result;

    public RetroFitLogin(String id, String pw) {
        this.userId = id;
        this.password = pw;
    }

    public String getId() {
        return userId;
    }

    public String getPw() {
        return password;
    }

    public String getToken() { return token; }

    public String getResult() { return result; }

    void setToken(String token) {
        this.token = token;
    }

    void setResult(String result) {
        this.result = result;
    }

    public String string() {
        return "{\n"
                + "'" + token + "'\n"
                + "'" + result + "'\n"
                +"}";
    }
}
