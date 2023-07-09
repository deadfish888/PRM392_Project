package com.example.prm392_project.data.model;

public class Comment {
    private int id;
    private String content ;
    private int bookId ;
    private String username;

    public Comment(int id, String content, int bookId, String username) {
        this.id = id;
        this.content = content;
        this.bookId = bookId;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
