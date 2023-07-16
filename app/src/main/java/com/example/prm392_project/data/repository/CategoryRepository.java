package com.example.prm392_project.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.prm392_project.data.DTO.Category.CategoryRequestDTO;
import com.example.prm392_project.data.model.Category;
import com.example.prm392_project.data.remote.APIManager.CategoryApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository {
    private static volatile CategoryRepository instance;
    private final CategoryApiManager apiManager;
    private final MutableLiveData<List<Category>> allCategories = new MutableLiveData();
    private final MutableLiveData<Category> categoryById = new MutableLiveData();
    private final MutableLiveData<Category> postCategory = new MutableLiveData();
    private final MutableLiveData<Integer> putResponse = new MutableLiveData();
    private final MutableLiveData<Integer> deleteResponse = new MutableLiveData<>();
    private CategoryRepository(CategoryApiManager apiManager) {        this.apiManager = apiManager;    }

    public static CategoryRepository getInstance(CategoryApiManager apiManager) {
        if (instance == null) {
            instance = new CategoryRepository(apiManager);
        }
        return instance;
    }

    public MutableLiveData<List<Category>> getAllCategories(){
        apiManager.GetAllCategories(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()){
                    List<Category> body = response.body();
                    allCategories.setValue(body);
                }else {
                    allCategories.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                allCategories.postValue(null);
            }
        });
        return allCategories;
    }
    public MutableLiveData<Category> getCategoryById(int id){
        apiManager.GetCategory(id, new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response.isSuccessful()){
                    Category body = response.body();
                    categoryById.setValue(body);
                }else {
                    categoryById.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                categoryById.postValue(null);
            }
        });
        return categoryById;
    }
    public MutableLiveData<Category> postCategory(CategoryRequestDTO category){
        apiManager.PostCategory(category, new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response.isSuccessful()){
                    Category body = response.body();
                    postCategory.setValue(body);
                }else {
                    postCategory.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {
                postCategory.postValue(null);
            }
        });
        return postCategory;
    }

    public MutableLiveData<Integer> putCategory(int id, CategoryRequestDTO category){
        apiManager.PutCategory(id, category, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                    if (response.isSuccessful()){
                        putResponse.setValue(200);
                    }else {
                        if (response.code() == 404) {
                            // Not found (404) error
                            putResponse.setValue(404);
                        }
                    }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                putResponse.postValue(404);
            }
        });
        return putResponse;
    }

    public MutableLiveData<Integer> deleteCategory(int id){
        apiManager.DeleteCategory(id, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()){
                    deleteResponse.setValue(200);
                }else {
                    if (response.code() == 404) {
                        // Not found (404) error
                        deleteResponse.setValue(404);
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                deleteResponse.postValue(404);
            }
        });
        return deleteResponse;
    }
}
