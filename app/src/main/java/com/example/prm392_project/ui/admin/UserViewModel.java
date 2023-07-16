package com.example.prm392_project.ui.admin;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.prm392_project.data.DTO.Auth.RegisterDTO;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.model.UserInfo;
import com.example.prm392_project.data.model.UserLoggedIn;
import com.example.prm392_project.data.repository.AuthRepository;
import com.example.prm392_project.data.repository.BookRepository;
import com.example.prm392_project.data.repository.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    public UserViewModel(UserRepository userRepository, AuthRepository authRepository) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
    }

    public LiveData<List<UserInfo>> getUsers() {
        return userRepository.getAllUsers();
    }

    public LiveData<Boolean> deleteUser(int id){
        return userRepository.deleteUser(id);
    }
    public LiveData<UserInfo> updateUser(UserInfo u){
        return userRepository.updateUser(u);
    }

    public LiveData<UserLoggedIn> registerUser(RegisterDTO u){
        return authRepository.register(u);
    }

}
