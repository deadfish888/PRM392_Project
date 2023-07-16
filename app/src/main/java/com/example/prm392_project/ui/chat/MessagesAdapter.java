package com.example.prm392_project.ui.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.R;
import com.example.prm392_project.data.model.Message;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {
    @NonNull
    private final Context context;
    private List<Message> messageList;
    private final String currentUsername;

    // Constructor
    public MessagesAdapter(@NonNull Context context, String currentUsername) {
        this.currentUsername = currentUsername;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the appropriate layout based on the viewType
        View itemView;
        if (viewType == MessageType.SENT) {
            itemView = LayoutInflater.from(context).inflate(R.layout.message_item_sent, parent, false);
        } else {
            itemView = LayoutInflater.from(context).inflate(R.layout.message_item_received, parent, false);
        }
        return new MessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageList.get(position);

        // Bind the message content to the respective TextView
        if (getItemViewType(position) == MessageType.SENT) {
            holder.messageTextViewSent.setText(message.getContent());
        } else {
            holder.messageTextViewReceived.setText(message.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return messageList == null ? 0 : messageList.size();
    }

    public void setItems(List<Message> messages){
        this.messageList = messages;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        // Determine the type of message based on the sender ID
        String sender = messageList.get(position).getSender_name();
        if (sender.equals(currentUsername)) {
            return MessageType.SENT;
        } else {
            return MessageType.RECEIVED;
        }
    }

    // ViewHolder class
    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView messageTextViewSent;
        public TextView messageTextViewReceived;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextViewSent = itemView.findViewById(R.id.messageTextViewSent);
            messageTextViewReceived = itemView.findViewById(R.id.messageTextViewReceived);
        }
    }

    // Constants for message types
    private static class MessageType {
        static final int SENT = 1;
        static final int RECEIVED = 2;
    }
}

