package com.example.prm392_project.ui.category;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.prm392_project.MainApplication;
import com.example.prm392_project.data.repository.BookRepository;
import com.example.prm392_project.data.repository.CategoryRepository;

public class CategoryViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CategoryViewModel.class)) {
            return (T) new CategoryViewModel(BookRepository.getInstance(MainApplication.bookApiManager),
                    CategoryRepository.getInstance(MainApplication.categoryApiManager));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
