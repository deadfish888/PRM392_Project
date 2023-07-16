package com.example.prm392_project.ui.chat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.MainThread;
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
import com.example.prm392_project.data.model.UserInfo;
import com.example.prm392_project.databinding.FragmentChatBinding;
import com.example.prm392_project.ui.MainActivity;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;
    private ChatAdapter chatAdapter;
    ChatViewModel chatViewModel;
    RecyclerView messageRecyclerView;
    List<Message> currentMessages;
    int currentChatId;
    int userId;
    Timer timer;
    Handler handler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        chatViewModel =
                new ViewModelProvider(this, new ChatViewModelFactory()).get(ChatViewModel.class);

        binding = FragmentChatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        messageRecyclerView = root.findViewById(R.id.chatRecyclerView);
        chatAdapter = new ChatAdapter(root.getContext(), MainActivity.username);
        messageRecyclerView.setAdapter(chatAdapter);
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
                    chatAdapter.setItems(messages);
                    LinearLayoutManager layoutManager = (LinearLayoutManager) messageRecyclerView
                            .getLayoutManager();
                    layoutManager.scrollToPositionWithOffset(messages.size()-1, 0);
                    updateMessages();
                });
            });
        });

        return root;
    }

    void updateMessages(){
        timer = new Timer();
        handler = new Handler(Looper.getMainLooper());
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // Retrieve new chat messages from the server
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        GetMessageDTO getMessageDTO = new GetMessageDTO(currentChatId,userId, 1);
//                        chatViewModel.loadMessage(getMessageDTO).observe(getViewLifecycleOwner(), messages -> {
//                            int currentMessageCount = currentMessages.size();
//
//                            if (currentMessageCount < messages.size()) {
//                                // Add the new messages to the existing list
//                                currentMessages.addAll(messages.subList(currentMessageCount, messages.size()));
//
//                                // Update the UI on the main thread
//
//                                // Notify the adapter that new items have been added
//                                chatAdapter.notifyItemRangeInserted(currentMessageCount, messages.size() - currentMessageCount);
//
//                                // Scroll to the last message
//                                messageRecyclerView.scrollToPosition(currentMessages.size() - 1);
//                            }
//                        });
//                // Update your chat UI with the new messages
//                    }
//                });
            }
        };
        // Schedule the TimerTask to run every X milliseconds
        long interval = 5000; // 5 seconds (adjust this value as needed)
        timer.scheduleAtFixedRate(timerTask, 0, interval);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}