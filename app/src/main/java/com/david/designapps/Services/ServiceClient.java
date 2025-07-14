package com.david.designapps.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {
    public Retrofit BuildRetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.0.2.2:5205/api/")
//                .baseUrl("http://192.168.18.23:5205/api/")
                .baseUrl("  https://5fc591e5b02b.ngrok-free.app/api/")
//                .baseUrl("http://192.168.10.57:5205/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
