package com.example.prm392_project;

import android.app.Application;

import com.example.prm392_project.data.remote.AuthApiManager;
import com.example.prm392_project.data.remote.BookApiManager;

public class MainApplication extends Application {
    public static AuthApiManager authApiManager;
    public static BookApiManager bookApiManager;
    @Override
    public void onCreate() {
        super.onCreate();
        authApiManager = AuthApiManager.getInstance();
    }

}
