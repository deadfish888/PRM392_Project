package com.example.prm392_project.data.remote;

import com.example.prm392_project.data.DTO.Book.BookCreateDTO;
import com.example.prm392_project.data.DTO.Book.BookUpdateDTO;
import com.example.prm392_project.data.DTO.Category.CategoryRequestDTO;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.model.Category;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryApiManager {
    private static ICategoryAPI service;
    private static CategoryApiManager apiManager;
    private CategoryApiManager(String token) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://139.59.115.128/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ICategoryAPI.class);
    }

    public static CategoryApiManager getInstance(String token) {
        if (apiManager == null) {
            apiManager = new CategoryApiManager(token);
        }
        return apiManager;
    }

    public void GetAllCategories(Callback<List<Category>> callback){
        Call<List<Category>> booksCall = service.getAllCategories();
        booksCall.enqueue(callback);
    }

    public void GetCategory(int id, Callback<Category> callback){
        Call<Category> bookCall = service.getCategoryById(id);
        bookCall.enqueue(callback);
    }

    public void PostCategory(CategoryRequestDTO Category, Callback<Category> callback){
        Call<Category> bookCall= service.postCategory(Category);
        bookCall.enqueue(callback);
    }

    public void PutCategory(int id, CategoryRequestDTO Category, Callback<Void> callback){
        Call<Void> bookCall = service.putCategory(id, Category);
        bookCall.enqueue(callback);
    }

    public void DeleteCategory(int id, Callback<Void> callback){
        Call<Void> responseCall = service.deleteCategory(id);
        responseCall.enqueue(callback);
    }
}
