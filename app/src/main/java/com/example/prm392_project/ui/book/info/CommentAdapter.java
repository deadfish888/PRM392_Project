package com.example.prm392_project.ui.book.info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_project.R;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.model.Comment;
import com.example.prm392_project.ui.common.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    @NonNull
    private final Context context;
    private List<Comment> items = new ArrayList();
    private final OnItemClickListener onItemClickListener;
    public CommentAdapter(@NonNull Context context, OnItemClickListener itemClickListener) {
        this.context = context;
        this.onItemClickListener = itemClickListener;
    }
    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentAdapter.CommentViewHolder(LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentViewHolder holder, int position) {
        holder.setItem(items.get(position));
    }
    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
    public void setItems(List items) {
        this.items = items;
        this.notifyDataSetChanged();
    }
    class CommentViewHolder extends RecyclerView.ViewHolder {
        private final TextView comment_content;
        private final TextView comment_username;
        private final View item;
        CommentViewHolder(View item) {
            super(item);
            comment_content = item.findViewById(R.id.comment_content);
            comment_username = item.findViewById(R.id.comment_username);
            this.item = item;
        }
        private void setItem(Comment cmt){
            comment_content.setText(cmt.getContent());
            comment_username.setText(cmt.getUsername());
            item.setOnClickListener(view -> onItemClickListener.onItemClicked(view, cmt));
        }
    }
}
