package com.company.superandyeyev.atlantteamtask.common;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by DIMON on 09.10.2017.
 */

public class Adapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolderCreator.create(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {}

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
