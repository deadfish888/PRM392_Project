package com.example.prm392_project.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.data.LoginDataSource;
import com.example.prm392_project.data.LoginRepository;
import com.example.prm392_project.data.repository.AuthRepository;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(AuthRepository.getInstance(MainApplication.authApiManager));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}