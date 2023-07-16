package com.example.prm392_project.data.remote.Base;

import com.example.prm392_project.MainApplication;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseAPIManager<T> {

    public T GetService(String token,Class<T> apiService){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(MainApplication.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(apiService);
    }
}
