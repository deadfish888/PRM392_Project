package com.example.prm392_project.ui.admin;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.prm392_project.MainApplication;
import com.example.prm392_project.data.repository.AuthRepository;
import com.example.prm392_project.data.repository.UserRepository;

public class UserViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(UserRepository.getInstance(MainApplication.userApiManager), AuthRepository.getInstance(MainApplication.authApiManager));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
