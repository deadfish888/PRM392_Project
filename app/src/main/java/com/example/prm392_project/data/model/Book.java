package com.example.prm392_project.data.model;

public class Book {

    private int id;
    private String title;
    private String author;
    private String content;
    private Integer categoryId;
    private Category category;

    public Book() {
    }

    public Book(int id, String title, String author, String content, Integer categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Book id(int id) {
        setId(id);
        return this;
    }

    public Book title(String title) {
        setTitle(title);
        return this;
    }

    public Book author(String author) {
        setAuthor(author);
        return this;
    }

    public Book content(String content) {
        setContent(content);
        return this;
    }

    public Book categoryId(Integer categoryId) {
        setCategoryId(categoryId);
        return this;
    }

    public Book category(Category category) {
        setCategory(category);
        return this;
    }
}
