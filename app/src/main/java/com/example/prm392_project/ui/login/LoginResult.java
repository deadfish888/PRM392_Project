package com.example.prm392_project.ui.login;

import androidx.annotation.Nullable;

import com.example.prm392_project.data.model.UserLoggedIn;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    @Nullable
    private UserLoggedIn success;
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable UserLoggedIn success) {
        this.success = success;
    }

    @Nullable
    UserLoggedIn getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}