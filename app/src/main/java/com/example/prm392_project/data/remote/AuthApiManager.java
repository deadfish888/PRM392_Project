package com.example.prm392_project.data.remote;

import com.example.prm392_project.data.DTO.Login;
import com.example.prm392_project.data.model.User;

import retrofit2.Call;
import retrofit2.Callback;

public class AuthApiManager {
    private static IApi service;
    private static AuthApiManager apiManager;
    private AuthApiManager() {
        service = RetrofitService.Create();
    }
    public static AuthApiManager getInstance() {
        if (apiManager == null) {
            apiManager = new AuthApiManager();
        }
        return apiManager;
    }

    public void login(Login loginRequest, Callback<User> callback){
        Call<User> userCall = service.login(loginRequest);
        userCall.enqueue(callback);
    }
}
