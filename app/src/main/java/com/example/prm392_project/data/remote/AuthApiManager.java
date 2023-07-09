package com.example.prm392_project.data.remote;

import com.example.prm392_project.data.DTO.Auth.Login;
import com.example.prm392_project.data.model.UserLoggedIn;

import retrofit2.Call;
import retrofit2.Callback;

public class AuthApiManager {
    private static IAuthAPI service;
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

    public void login(Login loginRequest, Callback<UserLoggedIn> callback){
        Call<UserLoggedIn> userCall = service.login(loginRequest);
        userCall.enqueue(callback);
    }
}
