package com.example.prm392_project.data.remote;

import com.example.prm392_project.data.DTO.Book.BookCreateDTO;
import com.example.prm392_project.data.DTO.Book.BookUpdateDTO;
import com.example.prm392_project.data.model.Book;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IBookAPI {
    @GET("api/Books")
    Call<List<Book>> GetBooks();

    @GET("api/Books/{id}")
    Call<Book> GetBook(@Path("id")int id);

    @GET("api/Books/search")
    Call<List<Book>> SearchBook(@Query("author")String author, @Query("title")String title, @Query("categoryID") int categoryId);

    @POST("api/Books")
    Call<Book> PostBook(@Body BookCreateDTO book);

    @PUT("api/Books/{id}")
    Call<Book> PutBook(@Path("id")int id,@Body BookUpdateDTO book);

    @DELETE("api/Books/{id}")
    Call<Response> DeleteBook(@Path("id")int id);
}
