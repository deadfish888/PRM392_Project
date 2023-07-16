package com.example.prm392_project.data.remote.IAPIService;
import com.example.prm392_project.data.DTO.Auth.Login;
import com.example.prm392_project.data.DTO.Auth.RegisterDTO;
import com.example.prm392_project.data.model.UserLoggedIn;
import com.example.prm392_project.data.remote.Base.IAPI;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthAPI extends IAPI {
    @POST("api/Auth/login")
    Call<UserLoggedIn> login(@Body Login login);

    @POST("api/Auth/register")
    Call<UserLoggedIn> register(@Body RegisterDTO user);
}
