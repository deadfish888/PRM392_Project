package com.example.prm392_project.data.remote.APIManager;

import com.example.prm392_project.data.DTO.Book.BookCreateDTO;
import com.example.prm392_project.data.DTO.Book.BookUpdateDTO;
import com.example.prm392_project.data.model.Book;
import com.example.prm392_project.data.remote.Base.BaseAPIManager;
import com.example.prm392_project.data.remote.IAPIService.IBookAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class BookApiManager extends BaseAPIManager<IBookAPI> {
    private static IBookAPI service;
    private static BookApiManager apiManager;
    private BookApiManager(String token) {
        service = GetService(token,IBookAPI.class);
    }

    public static BookApiManager getInstance(String token) {
        if (apiManager == null) {
            apiManager = new BookApiManager(token);
        }
        return apiManager;
    }

    public void GetBooks(Callback<List<Book>> callback){
        Call<List<Book>> booksCall = service.GetBooks();
        booksCall.enqueue(callback);
    }

    public void GetBook(int id, Callback<Book> callback){
        Call<Book> bookCall = service.GetBook(id);
        bookCall.enqueue(callback);
    }

    public void SearchBooks(String author, String title, Integer categoryId, Callback<List<Book>> callback){
        Call<List<Book>> booksCall = service.SearchBooks(author, title, categoryId);
        booksCall.enqueue(callback);
    }

    public void PostBook(BookCreateDTO book, Callback<Book> callback){
        Call<Book> bookCall= service.PostBook(book);
        bookCall.enqueue(callback);
    }

    public void PutBook(int id, BookUpdateDTO book, Callback<Book> callback){
        Call<Book> bookCall = service.PutBook(id, book);
        bookCall.enqueue(callback);
    }

    public void DeleteBook(int id, Callback<Void> callback){
        Call<Void> responseCall = service.DeleteBook(id);
        responseCall.enqueue(callback);
    }
}
