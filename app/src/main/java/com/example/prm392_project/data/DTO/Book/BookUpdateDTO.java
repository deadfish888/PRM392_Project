package com.example.prm392_project.data.DTO.Book;

import com.example.prm392_project.data.model.Book;

public class BookUpdateDTO {
    private String title;
    private String author;
    private String content;
    private int categoryId;

    public BookUpdateDTO(String title, String author, String content, int categoryId) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.categoryId = categoryId;
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

    public static BookUpdateDTO fromBook(Book book) {
        return new BookUpdateDTO(book.getTitle(), book.getAuthor(), book.getContent(), book.getCategoryId());
    }
}
