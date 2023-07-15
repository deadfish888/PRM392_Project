package com.example.prm392_project.data.model;

import java.sql.Timestamp;

public class Chat {
    String partner_username;
    int partner_id;
    String lastMessage;
    int chatID;

    public Chat(String partner_username, int partner_id, String lastMessage, int chatID) {
        this.partner_username = partner_username;
        this.partner_id = partner_id;
        this.lastMessage = lastMessage;
        this.chatID = chatID;
    }

    public String getPartner_username() {
        return partner_username;
    }

    public void setPartner_username(String partner_username) {
        this.partner_username = partner_username;
    }

    public int getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(int partner_id) {
        this.partner_id = partner_id;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }
}
