package com.example.prm392_project;

import android.app.Application;

import com.example.prm392_project.data.remote.AuthApiManager;
import com.example.prm392_project.data.remote.BookApiManager;
import com.example.prm392_project.data.remote.CategoryApiManager;
import com.example.prm392_project.data.remote.CommentApiManager;
import com.example.prm392_project.data.remote.UserApiManager;

public class MainApplication extends Application {
    public static String TOKEN = "abcxyz";
    public static AuthApiManager authApiManager;
    public static BookApiManager bookApiManager;
    public static CategoryApiManager categoryApiManager;
    public static CommentApiManager commentApiManager;
    public static UserApiManager userApiManager;
    @Override
    public void onCreate() {
        super.onCreate();
        authApiManager = AuthApiManager.getInstance();
    }

    public static void setUpManager(){
        bookApiManager = BookApiManager.getInstance(TOKEN);
        categoryApiManager = CategoryApiManager.getInstance(TOKEN);
        commentApiManager = CommentApiManager.getInstance(TOKEN);
    }

}
