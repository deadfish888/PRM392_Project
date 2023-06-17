package com.example.prm392_project;

import android.app.Application;

import com.example.prm392_project.data.remote.AuthApiManager;

public class MainApplication extends Application {
    public static AuthApiManager authApiManager;
    @Override
    public void onCreate() {
        super.onCreate();
        authApiManager = AuthApiManager.getInstance();
    }

}
