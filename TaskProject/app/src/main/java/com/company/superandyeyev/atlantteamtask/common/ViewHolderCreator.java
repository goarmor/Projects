package com.company.superandyeyev.atlantteamtask.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.superandyeyev.atlantteamtask.R;
import com.company.superandyeyev.atlantteamtask.ui.holderMVP.сomments.CommentsViewHolder;
import com.company.superandyeyev.atlantteamtask.ui.holderMVP.photo.PhotoViewHolder;
import com.company.superandyeyev.atlantteamtask.ui.holderMVP.posts.PostsViewHolder;
import com.company.superandyeyev.atlantteamtask.ui.holderMVP.todos.TodosViewHolder;
import com.company.superandyeyev.atlantteamtask.ui.holderMVP.users.UsersViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DIMON on 17.10.2017.
 */

public class ViewHolderCreator {

    private static List<BaseViewHolderCreator> listCreator;

    public static RecyclerView.ViewHolder create(ViewGroup parent, int viewType) {
        if (listCreator == null) {
            init();
        }
        return listCreator.get(viewType).create(parent, viewType);
    }

    private static void init() {
        listCreator = new ArrayList<>();
        listCreator.add((parent, viewType) -> {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts, parent, false);
            return new PostsViewHolder(v);
        });
        listCreator.add((parent, viewType) -> {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments, parent, false);
            return new CommentsViewHolder(v);
        });
        listCreator.add((parent, viewType) -> {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.users, parent, false);
            return new UsersViewHolder(v);
        });
        listCreator.add((parent, viewType) -> {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo, parent, false);
                return new PhotoViewHolder(v);
        });
        listCreator.add((parent, viewType) -> {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todos, parent, false);
            return new TodosViewHolder(v);
        });
    }

    private interface BaseViewHolderCreator {
        RecyclerView.ViewHolder create(ViewGroup parent, int viewType);
    }



}
