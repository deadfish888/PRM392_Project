package com.example.prm392_project.ui.admin;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prm392_project.R;
import com.example.prm392_project.data.model.UserInfo;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    public List<UserInfo> users;
    public UserAdapter(){}
    public void setListUser(List<UserInfo> users) {this.users = users;}
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_manager_item, parent, false);
        return new UserHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserHolder holder, int i) {
        try {
            String id = String.valueOf(users.get(i).getId());
            String username = users.get(i).getUsername();
            holder.txt_id.setText(id);
            holder.txt_username.setText(username);
            holder.txt_code.setText(users.get(i).getCredentialCode());
            holder.txt_phone.setText(users.get(i).getPhoneNumber());
        } catch (Exception e) {
            Log.i("exception", e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    class UserHolder extends RecyclerView.ViewHolder{

        TextView txt_id;
        TextView txt_username;
        TextView txt_code;
        TextView txt_phone;
        public UserHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_username = itemView.findViewById(R.id.txt_username);
            txt_code = itemView.findViewById(R.id.txt_code);
            txt_phone = itemView.findViewById(R.id.txt_phone);
        }
    }
}
