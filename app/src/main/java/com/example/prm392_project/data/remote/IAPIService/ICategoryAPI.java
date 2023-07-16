package com.example.prm392_project.data.remote.IAPIService;

import com.example.prm392_project.data.DTO.Category.CategoryRequestDTO;
import com.example.prm392_project.data.model.Category;
import com.example.prm392_project.data.remote.Base.IAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public  interface ICategoryAPI extends IAPI {
    @GET("api/Category")
    Call<List<Category>> getAllCategories();

    @POST("api/Category")
    Call<Category> postCategory(@Body CategoryRequestDTO category);

    @GET("api/Category/{id}")
    Call<Category> getCategoryById(@Path("id")int id);

    @PUT("api/Category/{id}")
    Call<Void> putCategory(@Path("id")int id,@Body CategoryRequestDTO category);

    @DELETE("api/Category/{id}")
    Call<Void> deleteCategory(@Path("id")int id);
}
