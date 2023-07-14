package com.example.prm392_project.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.repository.BookRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final BookRepository bookRepository;
    public HomeViewModel(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public LiveData<List<Book>> getBooks() {
        return bookRepository.getAllBooks();
    }
    public LiveData<List<Book>> searchBooks(String title, int categoryId){
        if (categoryId != 0){
            return bookRepository.searchBooks(null, null, categoryId);
        }
        if(title!=null){
            return bookRepository.searchBooks(null,title, null );
        }
        return bookRepository.getAllBooks();
    }
 }