package com.company.superandyeyev.atlantteamtask.rest;

import com.company.superandyeyev.atlantteamtask.common.ApiConstants;
import com.company.superandyeyev.atlantteamtask.rest.Model.Comment;
import com.company.superandyeyev.atlantteamtask.rest.Model.Photo;
import com.company.superandyeyev.atlantteamtask.rest.Model.Post;
import com.company.superandyeyev.atlantteamtask.rest.Model.Todo;
import com.company.superandyeyev.atlantteamtask.rest.Model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by DIMON on 10.10.2017.
 */

public interface ServiceApi {

    @GET(ApiConstants.POSTS_REQUEST)
    Observable<Post> getPost(@Path(ApiConstants.INDEX) int n);


    @GET(ApiConstants.COMMENTS_REQUEST)
    Observable<Comment> getComment(@Path(ApiConstants.INDEX) int n);


    @GET(ApiConstants.USERS_REQUEST)
    Observable<List<User>> getUsers();


    @GET(ApiConstants.PHOTO_REQUEST)
    Observable<Photo> getPhoto();


    @GET(ApiConstants.TODOS_REQUEST)
    Observable<Todo> getTodo(@Path(ApiConstants.INDEX) int n);

}
