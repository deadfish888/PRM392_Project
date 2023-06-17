package com.example.prm392_project.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.prm392_project.data.model.User;
import com.example.prm392_project.data.remote.AuthApiManager;

public class BookRepository {
    private static volatile BookRepository instance;
    private final AuthApiManager authApiManager;
    private final MutableLiveData<User> user = new MutableLiveData();

    private BookRepository(AuthApiManager authApiManager) {
        this.authApiManager = authApiManager;
    }

    public static BookRepository getInstance(AuthApiManager authApiManager) {
        if (instance == null) {
            instance = new BookRepository(authApiManager);
        }
        return instance;
    }
}
