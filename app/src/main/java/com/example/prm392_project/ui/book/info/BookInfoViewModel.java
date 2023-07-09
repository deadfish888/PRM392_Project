package com.example.prm392_project.ui.book.info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm392_project.data.model.Category;
import com.example.prm392_project.data.model.Comment;
import com.example.prm392_project.data.repository.CategoryRepository;
import com.example.prm392_project.data.repository.CommentRepository;

import java.util.List;

public class BookInfoViewModel extends ViewModel {
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;
    public BookInfoViewModel(CommentRepository commentRepository, CategoryRepository categoryRepository){
        this.commentRepository = commentRepository;
        this.categoryRepository = categoryRepository;
    }

    public LiveData<List<Comment>> getAllComments(int bookId){
        return commentRepository.getCommentsByBook(bookId);
    }
    public LiveData<List<Category>> getAllCategories(){
        return categoryRepository.getAllCategories();
    }
    public LiveData<Category> getCategoryById(int id){
        return categoryRepository.getCategoryById(id);
    }
}