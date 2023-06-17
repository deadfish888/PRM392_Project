package com.example.prm392_project.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    public static IAuthApi Create(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5147/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IAuthApi.class);
    }
}
