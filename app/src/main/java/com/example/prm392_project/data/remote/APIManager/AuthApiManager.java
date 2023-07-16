package com.example.prm392_project.data.remote.APIManager;

import com.example.prm392_project.data.DTO.Auth.Login;
import com.example.prm392_project.data.DTO.Auth.RegisterDTO;
import com.example.prm392_project.data.model.UserLoggedIn;
import com.example.prm392_project.data.remote.Base.BaseAPIManager;
import com.example.prm392_project.data.remote.IAPIService.IAuthAPI;
import com.example.prm392_project.data.remote.Base.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;

public class AuthApiManager extends BaseAPIManager<IAuthAPI> {
    private static IAuthAPI service;
    private static AuthApiManager apiManager;
    private AuthApiManager() {
        service = RetrofitService.Create();
    }
    private AuthApiManager(String token){
       this.GetService(token, IAuthAPI.class);
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
