package com.example.prm392_project.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final BookRepository bookRepository;
    public HomeViewModel(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public LiveData<List<Book>> getBooks() {
        MutableLiveData<List<Book>> books = new MutableLiveData<>();
        List<Book> list = new ArrayList<>();
        list.add(new Book(1,"Sapiens: A Brief History of Humankind","Yuval", "One hundred thousand years ago, at least six different species of humans inhabited Earth. Yet today there is only one—homo sapiens. What happened to the others? And what may happen to us?",5));
        list.add(new Book(2,"Sapiens: A Brief History of Humankind","Yuval", "One hundred thousand years ago, at least six different species of humans inhabited Earth. Yet today there is only one—homo sapiens. What happened to the others? And what may happen to us?",5));
        list.add(new Book(3,"Sapiens: A Brief History of Humankind","Yuval", "One hundred thousand years ago, at least six different species of humans inhabited Earth. Yet today there is only one—homo sapiens. What happened to the others? And what may happen to us?",5));
        list.add(new Book(4,"Sapiens: A Brief History of Humankind","Yuval", "One hundred thousand years ago, at least six different species of humans inhabited Earth. Yet today there is only one—homo sapiens. What happened to the others? And what may happen to us?",5));
        list.add(new Book(5,"Sapiens: A Brief History of Humankind","Yuval", "One hundred thousand years ago, at least six different species of humans inhabited Earth. Yet today there is only one—homo sapiens. What happened to the others? And what may happen to us?",5));
        list.add(new Book(6,"Sapiens: A Brief History of Humankind","Yuval", "One hundred thousand years ago, at least six different species of humans inhabited Earth. Yet today there is only one—homo sapiens. What happened to the others? And what may happen to us?",5));

        books.setValue(list);
        return books;
    }
}