package com.example.prm392_project.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.prm392_project.data.DTO.Comment.CommentCreateDTO;
import com.example.prm392_project.data.DTO.Comment.CommentDeleteDTO;
import com.example.prm392_project.data.model.Comment;
import com.example.prm392_project.data.remote.CommentApiManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentRepository {
    private static volatile CommentRepository instance;
    private final CommentApiManager apiManager;
    private final MutableLiveData<List<Comment>> commentsByBook = new MutableLiveData();
    private final MutableLiveData<Comment> postComment = new MutableLiveData();
    private final MutableLiveData<Integer> deleteResponse = new MutableLiveData<>();
    private CommentRepository(CommentApiManager apiManager) {        this.apiManager = apiManager;    }

    public static CommentRepository getInstance(CommentApiManager apiManager) {
        if (instance == null) {
            instance = new CommentRepository(apiManager);
        }
        return instance;
    }

    public MutableLiveData<List<Comment>> getCommentsByBook(int bookId){
        apiManager.getAllCommentsByBookId(bookId, new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful()){
                    List<Comment> body = response.body();
                    commentsByBook.setValue(body);
                }else {
                    commentsByBook.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                commentsByBook.postValue(null);
            }
        });
        return commentsByBook;
    }

    public MutableLiveData<Comment> postComment(CommentCreateDTO cmt){
        apiManager.postComment(cmt, new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (response.isSuccessful()){
                    Comment body = response.body();
                    postComment.setValue(body);
                }else{
                    postComment.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                postComment.postValue(null);
            }
        });
        return postComment;
    }

    public MutableLiveData<Integer> deleteComment(CommentDeleteDTO cmt){
        apiManager.deleteComment(cmt, new Callback<Void>() {
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
