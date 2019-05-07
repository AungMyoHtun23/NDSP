package com.example.ndsp.RetrofitService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    public static final String BASE_URL="http://128.199.217.182";

    public Retrofit getRetrofitService(){
        Retrofit service=new Retrofit.Builder().baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                return service;
    }
}
