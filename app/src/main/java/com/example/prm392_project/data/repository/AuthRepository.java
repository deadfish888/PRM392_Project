package com.example.prm392_project.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.prm392_project.data.model.Login;
import com.example.prm392_project.data.model.User;
import com.example.prm392_project.data.remote.AuthApiManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private static volatile AuthRepository instance;
    private final AuthApiManager authApiManager;
    private final MutableLiveData<User> user = new MutableLiveData();

    private AuthRepository(AuthApiManager authApiManager) {
        this.authApiManager = authApiManager;
    }

    public static AuthRepository getInstance(AuthApiManager authApiManager) {
        if (instance == null) {
            instance = new AuthRepository(authApiManager);
        }
        return instance;
    }

    public MutableLiveData<User> login(Login loginRequest) {
        authApiManager.login(loginRequest, new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User u = response.body();
                    user.setValue(u);
                } else {
                    user.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                user.postValue(null);
            }
        });

        return user;
    }
}
