package com.example.prm392_project.data.DTO.Chat;

public class GetMessageDTO {
    public int chatId;
    public int ownerId;
    public int partnerId;

    public GetMessageDTO(int chatId, int ownerId, int partnerId) {
        this.chatId = chatId;
        this.ownerId = ownerId;
        this.partnerId = partnerId;
    }
}
