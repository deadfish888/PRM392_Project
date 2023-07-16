package com.example.prm392_project.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.prm392_project.data.DTO.Chat.GetChatDTO;
import com.example.prm392_project.data.DTO.Chat.GetMessageDTO;
import com.example.prm392_project.data.DTO.Chat.SendMessageDTO;
import com.example.prm392_project.data.model.Chat;
import com.example.prm392_project.data.model.Message;
import com.example.prm392_project.data.remote.APIManager.ChatApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatRepository {
    private static volatile ChatRepository instance;
    private final ChatApiManager apiManager;
    private final MutableLiveData<List<Chat>> chats = new MutableLiveData<>();
    private final MutableLiveData<List<Message>> messages = new MutableLiveData<>();
    private final MutableLiveData<Boolean> sendMessage = new MutableLiveData<>();
    private ChatRepository(ChatApiManager apiManager) {
        this.apiManager = apiManager;
    }

    public static ChatRepository getInstance(ChatApiManager apiManager) {
        if (instance == null) {
            instance = new ChatRepository(apiManager);
        }
        return instance;
    }

    public MutableLiveData<List<Chat>> loadChat(GetChatDTO getChatDTO){
        apiManager.loadChat(getChatDTO, new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {
                if (response.isSuccessful()){
                    List<Chat> body = response.body();
                    chats.setValue(body);
                }else {
                    chats.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {
                chats.postValue(null);
            }
        });
        return chats;
    }

    public MutableLiveData<List<Message>> loadMessage(GetMessageDTO getMessageDTO){
        apiManager.loadMessage(getMessageDTO, new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()){
                    List<Message> body = response.body();
                    messages.setValue(body);
                }else {
                    messages.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                messages.postValue(null);
            }
        });
        return messages;
    }

    public MutableLiveData<Boolean> sendMessage(SendMessageDTO sendMessageDTO){
        apiManager.sendMessage(sendMessageDTO, new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()){
                    Boolean body = response.body();
                    sendMessage.setValue(body);
                }else {
                    sendMessage.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                sendMessage.postValue(null);
            }
        });
        return sendMessage;
    }
}
