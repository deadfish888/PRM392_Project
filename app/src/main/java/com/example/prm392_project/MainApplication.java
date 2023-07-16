package com.example.prm392_project;

import android.app.Application;

import com.example.prm392_project.data.remote.APIManager.AuthApiManager;
import com.example.prm392_project.data.remote.APIManager.BookApiManager;
import com.example.prm392_project.data.remote.APIManager.CategoryApiManager;
import com.example.prm392_project.data.remote.APIManager.ChatApiManager;
import com.example.prm392_project.data.remote.APIManager.CommentApiManager;
import com.example.prm392_project.data.remote.APIManager.UserApiManager;

public class MainApplication extends Application {
    public static final String API_URL = "http://139.59.115.128/";
    public static String TOKEN = "abcxyz";
    public static AuthApiManager authApiManager;
    public static BookApiManager bookApiManager;
    public static CategoryApiManager categoryApiManager;
    public static CommentApiManager commentApiManager;
    public static UserApiManager userApiManager;
    public static ChatApiManager chatApiManager;
    @Override
    public void onCreate() {
        super.onCreate();
        authApiManager = AuthApiManager.getInstance();
    }

    public static void setUpManager(){
        bookApiManager = BookApiManager.getInstance(TOKEN);
        categoryApiManager = CategoryApiManager.getInstance(TOKEN);
        commentApiManager = CommentApiManager.getInstance(TOKEN);
        chatApiManager = ChatApiManager.getInstance(TOKEN);
        userApiManager = UserApiManager.getInstance(TOKEN);
        AuthApiManager.clearInstance();
        authApiManager = AuthApiManager.getInstance(TOKEN);
    }

}
