package com.example.prm392_project.data.remote;

import com.example.prm392_project.data.DTO.Chat.GetChatDTO;
import com.example.prm392_project.data.DTO.Chat.GetMessageDTO;
import com.example.prm392_project.data.DTO.Chat.SendMessageDTO;
import com.example.prm392_project.data.model.Chat;
import com.example.prm392_project.data.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IChatAPI {
    @POST("api/Chat/loadChat")
    Call<List<Chat>> loadChat(@Body GetChatDTO getChatDTO);

    @POST("api/Chat/loadMessage")
    Call<List<Message>> loadMessage(@Body GetMessageDTO getMessageDTO);

    @POST("api/Chat/sendMessage")
    Call<Void> sendMessage(@Body SendMessageDTO sendMessageDTO);
}
