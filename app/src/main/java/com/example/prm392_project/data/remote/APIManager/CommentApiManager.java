package com.example.prm392_project.data.remote.APIManager;

import com.example.prm392_project.data.DTO.Comment.CommentCreateDTO;
import com.example.prm392_project.data.DTO.Comment.CommentDeleteDTO;
import com.example.prm392_project.data.model.Comment;
import com.example.prm392_project.data.remote.Base.BaseAPIManager;
import com.example.prm392_project.data.remote.IAPIService.ICommentAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CommentApiManager extends BaseAPIManager<ICommentAPI> {
    private static ICommentAPI service;
    private static CommentApiManager apiManager;
    private CommentApiManager(String token) {
        this.service = this.GetService(token, ICommentAPI.class);
    }

    public static CommentApiManager getInstance(String token) {
        if (apiManager == null) {
            apiManager = new CommentApiManager(token);
        }
        return apiManager;
    }

    public void getAllCommentsByBookId(int bookId, Callback<List<Comment>> callback){
        Call<List<Comment>> commentsByBookIdCall = service.getAllCommentsByBookId(bookId);
        commentsByBookIdCall.enqueue(callback);
    }

    public void postComment(CommentCreateDTO comment, Callback<Comment> callback){
        Call<Comment> commentCall = service.postComment(comment);
        commentCall.enqueue(callback);
    }

    public void deleteComment(CommentDeleteDTO comment, Callback<Void> callback){
        Call<Void> deleteCall = service.deleteComment(comment);
        deleteCall.enqueue(callback);
    }
}
