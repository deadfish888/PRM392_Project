package com.example.prm392_project.data.model;

public class Message {
    private int id;
    private int userId;
    private int chatId;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message(int id, int userId, int chatId, String content) {
        this.id = id;
        this.userId = userId;
        this.chatId = chatId;
        this.content = content;
    }
}
