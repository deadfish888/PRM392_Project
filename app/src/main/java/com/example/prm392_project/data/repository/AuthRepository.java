package com.example.prm392_project.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.prm392_project.data.DTO.Auth.Login;
import com.example.prm392_project.data.Result;
import com.example.prm392_project.data.model.User;
import com.example.prm392_project.data.remote.AuthApiManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private static volatile AuthRepository instance;
    private final AuthApiManager authApiManager;
    private final MutableLiveData<Result<User>> result = new MutableLiveData<>();
    private AuthRepository(AuthApiManager authApiManager) {
        this.authApiManager = authApiManager;
    }

    public static AuthRepository getInstance(AuthApiManager authApiManager) {
        if (instance == null) {
            instance = new AuthRepository(authApiManager);
        }
        return instance;
    }
    public MutableLiveData<Result<User>> login(Login loginRequest) {
        authApiManager.login(loginRequest, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User u = response.body();
                    result.setValue(new Result.Success<>(u));
                } else {
                    result.setValue(new Result.Error(new IOException("Login failed")));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                result.setValue(new Result.Error(new IOException("Error logging in", t)));
            }
        });

        return result;
    }
}
