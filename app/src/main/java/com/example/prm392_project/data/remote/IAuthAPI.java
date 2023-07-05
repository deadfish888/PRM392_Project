package com.example.prm392_project.data.remote;
import com.example.prm392_project.data.DTO.Auth.Login;
import com.example.prm392_project.data.DTO.Auth.RegisterDTO;
import com.example.prm392_project.data.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthAPI {
    @POST("api/Auth/login")
    Call<User> login(@Body Login login);

    @POST("api/Auth/register")
    Call<User> register(@Body RegisterDTO user);
}
