package com.example.prm392_project.data.remote.APIManager;

import com.example.prm392_project.data.DTO.Chat.GetChatDTO;
import com.example.prm392_project.data.DTO.Chat.GetMessageDTO;
import com.example.prm392_project.data.DTO.Chat.SendMessageDTO;
import com.example.prm392_project.data.model.Chat;
import com.example.prm392_project.data.model.Message;
import com.example.prm392_project.data.remote.Base.BaseAPIManager;
import com.example.prm392_project.data.remote.IAPIService.IChatAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ChatApiManager extends BaseAPIManager<IChatAPI> {
    private static IChatAPI service;
    private static ChatApiManager apiManager;
    private ChatApiManager(String token) {
        this.service=this.GetService(token, IChatAPI.class);
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
