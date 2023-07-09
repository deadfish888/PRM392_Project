package com.example.prm392_project.data.DTO.Comment;

public class CommentCreateDTO {
    public String content ;
    public int bookId ;
    public String username;

    public CommentCreateDTO(String content, int bookId, String username) {
        this.content = content;
        this.bookId = bookId;
        this.username = username;
    }
}
