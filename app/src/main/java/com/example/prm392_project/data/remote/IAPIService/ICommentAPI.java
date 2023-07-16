package com.example.prm392_project.data.remote.IAPIService;

import com.example.prm392_project.data.DTO.Comment.CommentCreateDTO;
import com.example.prm392_project.data.DTO.Comment.CommentDeleteDTO;
import com.example.prm392_project.data.model.Comment;
import com.example.prm392_project.data.remote.Base.IAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ICommentAPI extends IAPI {
    @GET("api/Comment")
    Call<List<Comment>> getAllCommentsByBookId(@Query("bookID")int bookId);

    @POST("api/Comment")
    Call<Comment> postComment(@Body CommentCreateDTO comment);

    @DELETE("api/Comment")
    Call<Void> deleteComment(@Body CommentDeleteDTO comment);
}
