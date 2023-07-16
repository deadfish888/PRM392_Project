package com.example.prm392_project.data.remote;

import com.example.prm392_project.data.model.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface IUserAPI {
    @GET("api/User")
    Call<UserInfo> getUserByUsername(@Query("username") String username);

    @GET("api/User/all")
    Call<List<UserInfo>> getAllUsers();

    @DELETE("api/User")
    Call<Boolean> deleteUser(@Query("id") int id);

    @PUT("api/User/all")
    Call<UserInfo> updateUser(@Body UserInfo user);
}
