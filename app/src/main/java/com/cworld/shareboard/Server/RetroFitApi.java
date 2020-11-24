package com.cworld.shareboard.Server;

import com.cworld.shareboard.data.RetroFitAutoLogin;
import com.cworld.shareboard.data.RetroFitClipboard;
import com.cworld.shareboard.data.RetroFitClipboardRequest;
import com.cworld.shareboard.data.RetroFitDeviceGet;
import com.cworld.shareboard.data.RetroFitDevicePost;
import com.cworld.shareboard.data.RetroFitLogin;
import com.cworld.shareboard.data.RetroFitRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetroFitApi {
    @POST("/signin")
    Call<RetroFitLogin> loginRequest(@Body RetroFitLogin retroFitLogin);

    @POST("/signup")
    Call<RetroFitRegister> registerRequest(@Body RetroFitRegister retroFitRegister);

    @GET("/clipboard")
    Call<RetroFitClipboard> getClipboard(@Header("Authorization") String token);

    @POST("/clipboard")
    Call<RetroFitClipboard> addClipboard(@Header("Authorization") String token, @Body RetroFitClipboardRequest retroFitClipboardRequest);

    @POST("/autologin")
    Call<RetroFitAutoLogin> autoLogin(@Header("Authorization") String token);

    @POST("/device")
    Call<RetroFitDevicePost> postDevice(@Body RetroFitDevicePost retroFitDevicePost);

    @GET("/device")
    Call<RetroFitDeviceGet> getDevice(@Header("Authorization") String token);
}
