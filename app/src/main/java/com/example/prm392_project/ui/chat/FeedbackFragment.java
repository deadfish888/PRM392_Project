package com.example.prm392_project.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.R;
import com.example.prm392_project.data.DTO.Chat.GetChatDTO;
import com.example.prm392_project.data.DTO.Chat.GetMessageDTO;
import com.example.prm392_project.data.model.Chat;
import com.example.prm392_project.data.model.Message;
import com.example.prm392_project.databinding.FragmentFeedbackBinding;
import com.example.prm392_project.ui.MainActivity;

import java.util.List;

public class FeedbackFragment extends Fragment {

    private FragmentFeedbackBinding binding;
    private MessagesAdapter messagesAdapter;
    ChatViewModel chatViewModel;
    RecyclerView messageRecyclerView;
    List<Message> currentMessages;
    int currentChatId;
    int userId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        chatViewModel =
                new ViewModelProvider(this, new ChatViewModelFactory()).get(ChatViewModel.class);

        binding = FragmentFeedbackBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        messageRecyclerView = root.findViewById(R.id.chatRecyclerView);
        messagesAdapter = new MessagesAdapter(root.getContext(), MainActivity.username);
        messageRecyclerView.setAdapter(messagesAdapter);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        chatViewModel.getUserByUsername(MainActivity.username).observe(getViewLifecycleOwner(), userInfo -> {
            GetChatDTO getChatDTO = new GetChatDTO(userInfo.getId());
            chatViewModel.loadChat(getChatDTO).observe(getViewLifecycleOwner(), chats -> {
                Chat currentChat = chats.get(0);
                currentChatId = currentChat.getChatID();
                userId = userInfo.getId();
                GetMessageDTO getMessageDTO = new GetMessageDTO(currentChatId, userId, 1);
                chatViewModel.loadMessage(getMessageDTO).observe(getViewLifecycleOwner(), messages -> {
                    currentMessages = messages;
                    messagesAdapter.setItems(messages);
                    messageRecyclerView.scrollToPosition(currentMessages.size() - 1);
                });
            });
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}