package com.example.prm392_project.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.model.User;
import com.example.prm392_project.data.remote.AuthApiManager;
import com.example.prm392_project.data.remote.BookApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepository {
    private static volatile BookRepository instance;
    private final BookApiManager bookApiManager;
    private final MutableLiveData<List<Book>> booksResult = new MutableLiveData();

    private BookRepository(BookApiManager bookApiManager) {
        this.bookApiManager = bookApiManager;
    }

    public static BookRepository getInstance(BookApiManager bookApiManager) {
        if (instance == null) {
            instance = new BookRepository(bookApiManager);
        }
        return instance;
    }

    public MutableLiveData<List<Book>> getBooks(){
        bookApiManager.GetBooks(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()){
                    List<Book> books = response.body();
                    booksResult.setValue(books);
                }else {
                    booksResult.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                booksResult.postValue(null);
            }
        });
        return booksResult;
    }
}
