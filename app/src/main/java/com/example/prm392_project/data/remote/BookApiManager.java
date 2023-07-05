package com.example.prm392_project.data.remote;

import com.example.prm392_project.data.model.Book;

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

public class BookApiManager {
    private static String TOKEN = null;
    private static IBookAPI service;
    private static BookApiManager apiManager;
    private BookApiManager() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + TOKEN)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://139.59.115.128/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(IBookAPI.class);
    }

    public static BookApiManager getInstance(String token) {
        if (apiManager == null) {
            TOKEN = token;
            apiManager = new BookApiManager();
        }
        return apiManager;
    }

    public void GetBooks(Callback<List<Book>> callback){
        Call<List<Book>> booksCall = service.GetBooks();
        booksCall.enqueue(callback);
    }

}
