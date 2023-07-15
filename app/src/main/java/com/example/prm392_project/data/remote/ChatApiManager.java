package com.example.prm392_project.data.remote;

import android.icu.util.Measure;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.data.DTO.Chat.GetChatDTO;
import com.example.prm392_project.data.DTO.Chat.GetMessageDTO;
import com.example.prm392_project.data.DTO.Chat.SendMessageDTO;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.model.Chat;
import com.example.prm392_project.data.model.Message;

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

public class ChatApiManager {
    private static IChatAPI service;
    private static ChatApiManager apiManager;
    private ChatApiManager(String token) {
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
                .baseUrl(MainApplication.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(IChatAPI.class);
    }

    public static ChatApiManager getInstance(String token) {
        if (apiManager == null) {
            apiManager = new ChatApiManager(token);
        }
        return apiManager;
    }

    public void loadChat(GetChatDTO getChatDTO, Callback<List<Chat>> callback){
        Call<List<Chat>> chatsCall = service.loadChat(getChatDTO);
        chatsCall.enqueue(callback);
    }

    public void loadMessage(GetMessageDTO message, Callback<List<Message>> callback){
        Call<List<Message>> messagesCall = service.loadMessage(message);
        messagesCall.enqueue(callback);
    }

    public void sendMessage(SendMessageDTO sendMessageDTO, Callback<Boolean> callback){
        Call<Boolean> sendMessageCall = service.sendMessage(sendMessageDTO);
        sendMessageCall.enqueue(callback);
    }
}
