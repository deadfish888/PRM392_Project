package com.example.prm392_project.data.remote.IAPIService;

import com.example.prm392_project.data.DTO.User.UserUpdateDTO;
import com.example.prm392_project.data.model.UserInfo;
import com.example.prm392_project.data.remote.Base.IAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface IUserAPI extends IAPI {
    @GET("api/User")
    Call<UserInfo> getUserByUsername(@Query("username") String username);

    @GET("api/User/all")
    Call<List<UserInfo>> getAllUsers();

    @PUT("api/User/phone")
    Call<UserInfo> PutUserPhone(@Body UserUpdateDTO user);

    @DELETE("api/User")
    Call<Boolean> deleteUser(@Query("id") int id);

    @PUT("api/User/all")
    Call<UserInfo> updateUser(@Body UserInfo user);
}
