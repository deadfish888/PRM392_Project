package com.example.prm392_project.ui.chat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm392_project.data.DTO.Chat.GetChatDTO;
import com.example.prm392_project.data.DTO.Chat.GetMessageDTO;
import com.example.prm392_project.data.DTO.Chat.SendMessageDTO;
import com.example.prm392_project.data.model.Chat;
import com.example.prm392_project.data.model.Message;
import com.example.prm392_project.data.model.UserInfo;
import com.example.prm392_project.data.repository.ChatRepository;
import com.example.prm392_project.data.repository.UserRepository;

import java.util.List;

public class ChatViewModel extends ViewModel {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public ChatViewModel(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }
    public LiveData<List<Chat>> loadChat(GetChatDTO getChatDTO){
        return chatRepository.loadChat(getChatDTO);
    }
    public LiveData<List<Message>> loadMessage(GetMessageDTO getMessageDTO){
        return chatRepository.loadMessage(getMessageDTO);
    }
    public LiveData<Boolean> sendMessage(SendMessageDTO sendMessageDTO){
        return chatRepository.sendMessage(sendMessageDTO);
    }
    public LiveData<UserInfo> getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }
}