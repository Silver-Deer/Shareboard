package com.cworld.shareboard.Server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {
//    public static final String Base_URL = "10.80.163.222:8080";
//
//    public static RetroFitApi getApiService() {
//        return (RetroFitApi) getInstance().create(RetroFitApi.class);
//    }
//
//    public static Retrofit getInstance() {
//        Gson gson = new GsonBuilder().setLenient().create();
//        return new Retrofit.Builder()
//                .baseUrl(Base_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//    }
//

    private static RetroFitClient instance;
    private RetroFitApi api;

    private RetroFitClient() {
        String url = "http://10.80.163.75:8080";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(RetroFitApi.class);
    }

    public static RetroFitClient getInstance() {
        if(instance == null) instance = new RetroFitClient();
        return instance;
    }

    public RetroFitApi getApi() { return api; }
}
