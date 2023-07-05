package com.example.prm392_project.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.data.LoginRepository;
import com.example.prm392_project.data.Result;
import com.example.prm392_project.data.DTO.Auth.Login;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.model.User;
import com.example.prm392_project.R;
import com.example.prm392_project.data.remote.BookApiManager;
import com.example.prm392_project.data.repository.AuthRepository;

import java.io.IOException;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private AuthRepository authRepository;
    LoginViewModel(AuthRepository loginRepository) {
        this.authRepository = loginRepository;
    }
    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }
    public void setLoginResult(LoginResult loginResult) {
        this.loginResult.setValue(loginResult);
    }
    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public LiveData<Result<User>> login(String username, String password) {
        Login loginRequest = new Login(username, password);
        return authRepository.login(loginRequest);
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 4;
    }
}