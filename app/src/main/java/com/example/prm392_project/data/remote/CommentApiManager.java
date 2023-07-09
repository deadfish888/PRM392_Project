package com.example.prm392_project.data.remote;

import com.example.prm392_project.data.DTO.Comment.CommentCreateDTO;
import com.example.prm392_project.data.DTO.Comment.CommentDeleteDTO;
import com.example.prm392_project.data.model.Comment;

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

public class CommentApiManager {
    private static ICommentAPI service;
    private static CommentApiManager apiManager;
    private CommentApiManager(String token) {
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
        service = retrofit.create(ICommentAPI.class);
    }

    public static CommentApiManager getInstance(String token) {
        if (apiManager == null) {
            apiManager = new CommentApiManager(token);
        }
        return apiManager;
    }

    public void getAllCommentsByBookId(int bookId, Callback<List<Comment>> callback){
        Call<List<Comment>> commentsByBookIdCall = service.getAllCommentsByBookId(bookId);
        commentsByBookIdCall.enqueue(callback);
    }

    public void postComment(CommentCreateDTO comment, Callback<Comment> callback){
        Call<Comment> commentCall = service.postComment(comment);
        commentCall.enqueue(callback);
    }

    public void deleteComment(CommentDeleteDTO comment, Callback<Void> callback){
        Call<Void> deleteCall = service.deleteComment(comment);
        deleteCall.enqueue(callback);
    }
}
