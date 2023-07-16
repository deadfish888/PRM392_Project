package com.example.prm392_project.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.prm392_project.data.DTO.Book.BookCreateDTO;
import com.example.prm392_project.data.DTO.Book.BookUpdateDTO;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.remote.BookApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookRepository {
    private static volatile BookRepository instance;
    private final BookApiManager bookApiManager;
    private final MutableLiveData<List<Book>> allBooks = new MutableLiveData();
    private final MutableLiveData<Book> bookById = new MutableLiveData();
    private final MutableLiveData<List<Book>> searchBooks = new MutableLiveData();
    private final MutableLiveData<Book> postBook = new MutableLiveData();
    private final MutableLiveData<Book> putBook = new MutableLiveData();
    private final MutableLiveData<Integer> deleteResponse = new MutableLiveData<>();
    private BookRepository(BookApiManager bookApiManager) {
        this.bookApiManager = bookApiManager;
    }

    public static BookRepository getInstance(BookApiManager bookApiManager) {
        if (instance == null) {
            instance = new BookRepository(bookApiManager);
        }
        return instance;
    }

    public MutableLiveData<List<Book>> getAllBooks(){
        bookApiManager.GetBooks(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()){
                    List<Book> body = response.body();
                    allBooks.setValue(body);
                }else {
                    allBooks.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                allBooks.postValue(null);
            }
        });
        return allBooks;
    }

    public MutableLiveData<Book> getBookById(int id){
        bookApiManager.GetBook(id, new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()){
                    Book body = response.body();
                    bookById.setValue(body);
                }else {
                    bookById.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                bookById.postValue(null);
            }
        });
        return bookById;
    }

    public MutableLiveData<List<Book>> searchBooks(String author, String title, Integer categoryId){
        bookApiManager.SearchBooks(author, title, categoryId, new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful()){
                    List<Book> body = response.body();
                    searchBooks.setValue(body);
                }else {
                    searchBooks.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                searchBooks.postValue(null);
            }
        });
        return searchBooks;
    }

    public MutableLiveData<Book> postBook(BookCreateDTO book){
        bookApiManager.PostBook(book, new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()){
                    Book body = response.body();
                    postBook.setValue(body);
                }else {
                    postBook.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                postBook.postValue(null);
            }
        });
        return postBook;
    }

    public MutableLiveData<Book> putBook(int id, BookUpdateDTO book){
        bookApiManager.PutBook(id, book, new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()){
                    Book body = response.body();
                    putBook.setValue(body);
                }else {
                    putBook.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                putBook.postValue(null);
            }
        });
        return putBook;
    }

    public MutableLiveData<Integer> deleteBook(int id){
        bookApiManager.DeleteBook(id, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    deleteResponse.setValue(200);
                }else {
                    if (response.code() == 404) {
                        // Not found (404) error
                        deleteResponse.setValue(404);
                    } else if (response.code() == 400) {
                        // Bad request (400) error
                        deleteResponse.setValue(400);
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                deleteResponse.setValue(404);
            }
        });
        return deleteResponse;
    }
}
