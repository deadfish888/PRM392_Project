package com.example.prm392_project.ui.chat;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.data.repository.ChatRepository;
import com.example.prm392_project.data.repository.UserRepository;

public class ChatViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ChatViewModel.class)) {
            return (T) new ChatViewModel(ChatRepository.getInstance(MainApplication.chatApiManager),
                    UserRepository.getInstance(MainApplication.userApiManager));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
