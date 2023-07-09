package com.example.prm392_project.data.remote;

import com.example.prm392_project.data.model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IUserAPI {
    @GET("api/User")
    Call<UserInfo> getUserByUsername(@Query("username") String username);

    @GET("api/User")
    Call<List<UserInfo>> getAllUsers();
}
