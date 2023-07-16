package com.example.prm392_project.data.remote.Base;

import com.example.prm392_project.data.remote.IAPIService.IAuthAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    public static IAuthAPI Create(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://139.59.115.128/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IAuthAPI.class);
    }
}
