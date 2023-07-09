package com.example.prm392_project.ui.book.info;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.data.repository.BookRepository;
import com.example.prm392_project.data.repository.CategoryRepository;
import com.example.prm392_project.data.repository.CommentRepository;
import com.example.prm392_project.ui.MainActivity;
import com.example.prm392_project.ui.home.HomeViewModel;

public class BookInfoViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BookInfoViewModel.class)) {
            return (T) new BookInfoViewModel(CommentRepository.getInstance(MainApplication.commentApiManager),
                    CategoryRepository.getInstance(MainApplication.categoryApiManager));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
