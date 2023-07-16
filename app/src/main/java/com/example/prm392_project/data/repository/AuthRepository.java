package com.example.prm392_project.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.prm392_project.data.DTO.Auth.Login;
import com.example.prm392_project.data.DTO.Auth.RegisterDTO;
import com.example.prm392_project.data.Result;
import com.example.prm392_project.data.model.UserLoggedIn;
import com.example.prm392_project.data.remote.APIManager.AuthApiManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private static volatile AuthRepository instance;
    private final AuthApiManager authApiManager;
    private final MutableLiveData<Result<UserLoggedIn>> login = new MutableLiveData<>();
    private final MutableLiveData<UserLoggedIn> register = new MutableLiveData<>();
    private AuthRepository(AuthApiManager authApiManager) {
        this.authApiManager = authApiManager;
    }

    public static AuthRepository getInstance(AuthApiManager authApiManager) {
        if (instance == null) {
            instance = new AuthRepository(authApiManager);
        }
        return instance;
    }
    public MutableLiveData<Result<UserLoggedIn>> login(Login loginRequest) {
        authApiManager.login(loginRequest, new Callback<UserLoggedIn>() {
            @Override
            public void onResponse(Call<UserLoggedIn> call, Response<UserLoggedIn> response) {
                if (response.isSuccessful()) {
                    UserLoggedIn u = response.body();
                    login.setValue(new Result.Success<>(u));
                } else {
                    login.setValue(new Result.Error(new IOException("Login failed")));
                }
            }

            @Override
            public void onFailure(Call<UserLoggedIn> call, Throwable t) {
                login.setValue(new Result.Error(new IOException("Error logging in", t)));
            }
        });

        return login;
    }

    public MutableLiveData<UserLoggedIn> register(RegisterDTO registerRequest) {
        authApiManager.register(registerRequest, new Callback<UserLoggedIn>() {
            @Override
            public void onResponse(Call<UserLoggedIn> call, Response<UserLoggedIn> response) {
                if (response.isSuccessful()) {
                    UserLoggedIn u = response.body();
                    register.setValue(u);
                } else {
                    register.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserLoggedIn> call, Throwable t) {
                register.postValue(null);
            }
        });

        return register;
    }
}
