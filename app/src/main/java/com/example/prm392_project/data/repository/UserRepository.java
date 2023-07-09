package com.example.prm392_project.data.repository;

import androidx.lifecycle.MutableLiveData;
import com.example.prm392_project.data.model.UserInfo;
import com.example.prm392_project.data.remote.UserApiManager;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static volatile UserRepository instance;
    private final UserApiManager apiManager;
    private final MutableLiveData<List<UserInfo>> allUsers = new MutableLiveData();
    private final MutableLiveData<UserInfo> user = new MutableLiveData();
    private UserRepository(UserApiManager apiManager) {        this.apiManager = apiManager;    }

    public static UserRepository getInstance(UserApiManager apiManager) {
        if (instance == null) {
            instance = new UserRepository(apiManager);
        }
        return instance;
    }

    public MutableLiveData<List<UserInfo>> getAllUsers(){
        apiManager.getAllUsers(new Callback<List<UserInfo>>() {
            @Override
            public void onResponse(Call<List<UserInfo>> call, Response<List<UserInfo>> response) {
                if (response.isSuccessful()){
                    List<UserInfo> body = response.body();
                    allUsers.setValue(body);
                }else {
                    allUsers.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<UserInfo>> call, Throwable t) {
                allUsers.postValue(null);
            }
        });
        return  allUsers;
    }

    public MutableLiveData<UserInfo> getUserByUsername(String username){
        apiManager.getUserByUsername(username, new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful()){
                    UserInfo body = response.body();
                    user.setValue(body);
                }else{
                    user.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                user.postValue(null);
            }
        });
        return user;
    }
}
