package com.example.prm392_project.data.remote;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.data.DTO.Auth.Login;
import com.example.prm392_project.data.DTO.Auth.RegisterDTO;
import com.example.prm392_project.data.model.UserLoggedIn;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthApiManager {
    private static IAuthAPI service;
    private static AuthApiManager apiManager;
    private AuthApiManager() {
        service = RetrofitService.Create();
    }
    private AuthApiManager(String token){
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
        service = retrofit.create(IAuthAPI.class);
    }
    public static AuthApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new AuthApiManager();
        }
        return apiManager;
    }

    public static void clearInstance() {
        apiManager = null;
    }

    public static AuthApiManager getInstance(String token) {
        if (apiManager == null) {
            apiManager = new AuthApiManager(token);
        }
        return apiManager;
    }

    public void login(Login loginRequest, Callback<UserLoggedIn> callback){
        Call<UserLoggedIn> userCall = service.login(loginRequest);
        userCall.enqueue(callback);
    }

    public void register(RegisterDTO register, Callback<UserLoggedIn> callback){
        Call<UserLoggedIn> registerCall = service.register(register);
        registerCall.enqueue(callback);
    }
}
