package com.example.prm392_project.data.DTO.Chat;

public class SendMessageDTO {
    public int sender_id;
    public int receiver_id;
    public String content;

    public SendMessageDTO(int sender_id, int receiver_id, String content) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.content = content;
    }
}
