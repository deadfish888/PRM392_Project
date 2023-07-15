package com.example.prm392_project.data.model;

public class Message {
    String sender_name;
    String content;
    String receiver_name;

    public Message(String sender_name, String content, String receiver_name) {
        this.sender_name = sender_name;
        this.content = content;
        this.receiver_name = receiver_name;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }
}
