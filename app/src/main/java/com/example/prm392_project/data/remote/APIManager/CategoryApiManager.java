package com.example.prm392_project.data.remote.APIManager;

import com.example.prm392_project.data.DTO.Category.CategoryRequestDTO;
import com.example.prm392_project.data.model.Category;
import com.example.prm392_project.data.remote.Base.BaseAPIManager;
import com.example.prm392_project.data.remote.IAPIService.ICategoryAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CategoryApiManager extends BaseAPIManager<ICategoryAPI> {
    private static ICategoryAPI service;
    private static CategoryApiManager apiManager;
    private CategoryApiManager(String token) {
        this.service=this.GetService(token, ICategoryAPI.class);
    }

    public static CategoryApiManager getInstance(String token) {
        if (apiManager == null) {
            apiManager = new CategoryApiManager(token);
        }
        return apiManager;
    }

    public void GetAllCategories(Callback<List<Category>> callback){
        Call<List<Category>> booksCall = service.getAllCategories();
        booksCall.enqueue(callback);
    }

    public void GetCategory(int id, Callback<Category> callback){
        Call<Category> bookCall = service.getCategoryById(id);
        bookCall.enqueue(callback);
    }

    public void PostCategory(CategoryRequestDTO Category, Callback<Category> callback){
        Call<Category> bookCall= service.postCategory(Category);
        bookCall.enqueue(callback);
    }

    public void PutCategory(int id, CategoryRequestDTO Category, Callback<Void> callback){
        Call<Void> bookCall = service.putCategory(id, Category);
        bookCall.enqueue(callback);
    }

    public void DeleteCategory(int id, Callback<Void> callback){
        Call<Void> responseCall = service.deleteCategory(id);
        responseCall.enqueue(callback);
    }
}
