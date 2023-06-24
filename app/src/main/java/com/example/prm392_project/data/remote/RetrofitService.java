package com.example.prm392_project.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    public static IApi Create(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://139.59.115.128")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IApi.class);
    }
}
