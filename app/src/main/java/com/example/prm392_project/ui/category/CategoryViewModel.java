package com.example.prm392_project.ui.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.model.Category;
import com.example.prm392_project.data.repository.BookRepository;
import com.example.prm392_project.data.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewModel extends ViewModel {
    public final CategoryRepository categoryRepository;
    public final BookRepository bookRepository;

    public CategoryViewModel(BookRepository bookRepository, CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    public LiveData<List<Category>> getAllCategories(){
        return categoryRepository.getAllCategories();
    }

    public LiveData<List<Book>> getAllBooks(){
        return bookRepository.getAllBooks();
    }


}
