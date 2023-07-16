package com.example.prm392_project.data.remote.APIManager;

import com.example.prm392_project.data.DTO.User.UserUpdateDTO;
import com.example.prm392_project.data.model.UserInfo;
import com.example.prm392_project.data.remote.Base.BaseAPIManager;
import com.example.prm392_project.data.remote.IAPIService.IUserAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class UserApiManager extends BaseAPIManager<IUserAPI> {
    private static IUserAPI service;
    private static UserApiManager apiManager;
    private UserApiManager(String token) {
        this.service = this.GetService(token, IUserAPI.class);
    }

    public static UserApiManager getInstance(String token) {
        if (apiManager == null) {
            apiManager = new UserApiManager(token);
        }
        return apiManager;
    }

    public void getUserByUsername(String username, Callback<UserInfo> callback){
        Call<UserInfo> userInfoCall = service.getUserByUsername(username);
        userInfoCall.enqueue(callback);
    }

    public void getAllUsers(Callback<List<UserInfo>> callback){
        Call<List<UserInfo>> usersCall = service.getAllUsers();
        usersCall.enqueue(callback);
    }

    public void UpdateUserPhone(Callback<UserInfo> callback, UserUpdateDTO userUpdateDTO){
        Call<UserInfo> usersCall = service.PutUserPhone(userUpdateDTO);
        usersCall.enqueue(callback);
    }
}
