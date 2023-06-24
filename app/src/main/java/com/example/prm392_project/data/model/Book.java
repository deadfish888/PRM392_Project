package com.example.prm392_project.data.model;

public class Book {
    private int Id;
    private String title;
    private String author;
    private String content;
    private int categoryId;

    public Book(int id, String title, String author, String content, int categoryId) {
        Id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.categoryId = categoryId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}