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
}