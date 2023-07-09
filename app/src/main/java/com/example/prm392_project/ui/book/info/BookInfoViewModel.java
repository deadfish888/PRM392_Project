package com.example.prm392_project.ui.book.info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm392_project.data.model.Comment;
import com.example.prm392_project.data.repository.CommentRepository;

import java.util.List;

public class BookInfoViewModel extends ViewModel {
    private final CommentRepository commentRepository;
    public BookInfoViewModel(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    public LiveData<List<Comment>> getAllComments(int bookId){
        return commentRepository.getCommentsByBook(bookId);
    }
}