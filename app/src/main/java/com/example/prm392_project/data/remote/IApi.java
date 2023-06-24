package com.example.prm392_project.data.remote;
import com.example.prm392_project.data.DTO.Auth.Login;
import com.example.prm392_project.data.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IApi {
    @POST("api/Auth/login")
    Call<User> login(@Body Login login);
}
