package com.example.prm392_project.data.remote;
import com.example.prm392_project.data.model.Login;
import com.example.prm392_project.data.model.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IAuthApi {
    @POST("api/auth/login")
    Call<User> login(@Body Login login);
}
