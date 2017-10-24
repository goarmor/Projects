package com.company.superandyeyev.atlantteamtask.repository;

import android.content.Context;
import android.graphics.Bitmap;

import com.company.superandyeyev.atlantteamtask.rest.Model.Comment;
import com.company.superandyeyev.atlantteamtask.rest.Model.Post;
import com.company.superandyeyev.atlantteamtask.rest.Model.Todo;
import com.company.superandyeyev.atlantteamtask.rest.Model.User;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by DIMON on 15.10.2017.
 */

public interface Repository {

    Observable<Post> getPost(int n);

    Observable<Comment> getComment(int n);

    Observable<List<User>> getUsers();

    Observable<Bitmap> getPhoto(Context context);

    Observable<Todo> getTodo();
}
