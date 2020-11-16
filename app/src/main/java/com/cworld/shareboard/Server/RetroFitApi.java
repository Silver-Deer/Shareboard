package com.cworld.shareboard.Server;

import com.cworld.shareboard.data.RetroFitAutoLogin;
import com.cworld.shareboard.data.RetroFitClipboard;
import com.cworld.shareboard.data.RetroFitLogin;
import com.cworld.shareboard.data.RetroFitRegister;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RetroFitApi {
    @POST("/signin")
    Call<RetroFitLogin> loginRequest(@Body RetroFitLogin retroFitLogin);

    @POST("/signup")
    Call<RetroFitRegister> registerRequest(@Body RetroFitRegister retroFitRegister);

    @GET("/clipboard")
    Call<RetroFitClipboard> getClipboard(@Header("Authorization") String token);


    @POST("/autologin")
    Call<RetroFitAutoLogin> autoLogin(@Header("Authorization") String token);
}
