package com.example.prm392_project.ui.chat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.R;
import com.example.prm392_project.data.DTO.Chat.GetChatDTO;
import com.example.prm392_project.data.DTO.Chat.GetMessageDTO;
import com.example.prm392_project.data.DTO.Chat.SendMessageDTO;
import com.example.prm392_project.data.DTO.Comment.CommentCreateDTO;
import com.example.prm392_project.data.model.Chat;
import com.example.prm392_project.data.model.Comment;
import com.example.prm392_project.data.model.Message;
import com.example.prm392_project.databinding.FragmentFeedbackBinding;
import com.example.prm392_project.ui.MainActivity;

import java.util.List;

public class FeedbackFragment extends Fragment {

    private FragmentFeedbackBinding binding;
    View root;
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
        root = binding.getRoot();
        ContentLoadingProgressBar progress = root.findViewById(R.id.feedback_progress);
        messageRecyclerView = root.findViewById(R.id.chatRecyclerView);
        messagesAdapter = new MessagesAdapter(root.getContext(), MainActivity.username);
        messageRecyclerView.setAdapter(messagesAdapter);
        messageRecyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        progress.show();
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
                    progress.hide();
                });
            });
        });


        OnBtnSendClicked();
        return root;
    }

    private void OnBtnSendClicked() {
        EditText edtMessage = root.findViewById(R.id.edtMessage);
        Button btnSend = root.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(v -> {
            String content = edtMessage.getText().toString().trim();
            if (content.isEmpty()) {
                return;
            }
            this.hideSoftKeyboard(getActivity(), v);
            edtMessage.setText("");
            SendMessageDTO sendMessageDTO = new SendMessageDTO(userId,1, content);
            chatViewModel.sendMessage(sendMessageDTO).observe(getViewLifecycleOwner(), response ->{
                if (response != null && response){
                    Message newMessage = new Message(MainActivity.username, content, "admin");
                    messagesAdapter.addMessage(newMessage);
                    messageRecyclerView.scrollToPosition(currentMessages.size()-1);
                }
            });

        });
    }
    public void hideSoftKeyboard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}