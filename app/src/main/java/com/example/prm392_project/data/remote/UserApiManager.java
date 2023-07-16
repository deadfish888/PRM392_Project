package com.example.prm392_project.data.remote;

import com.example.prm392_project.data.model.UserInfo;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserApiManager {
    private static IUserAPI service;
    private static UserApiManager apiManager;
    private UserApiManager(String token) {
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
                .baseUrl("http://139.59.115.128/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(IUserAPI.class);
    }

    public static UserApiManager getInstance(String token) {
        if (apiManager == null) {
            apiManager = new UserApiManager(token);
        }
        return apiManager;
    }

    public void getUserByUsername(String username, Callback<UserInfo> callback){
        Call<UserInfo> userInfoCall = service.getUserByUsername(username);
        userInfoCall.enqueue(callback);
    }

    public void getAllUsers(Callback<List<UserInfo>> callback){
        Call<List<UserInfo>> usersCall = service.getAllUsers();
        usersCall.enqueue(callback);
    }

    public void deleteUser(Callback<Boolean> callback, int id){
        Call<Boolean> userDeleteCall = service.deleteUser(id);
        userDeleteCall.enqueue(callback);
    }
    public void updateUser(UserInfo user, Callback<UserInfo> callback){
        Call<UserInfo> userInfoCall = service.updateUser(user);
        userInfoCall.enqueue(callback);
    }
}
