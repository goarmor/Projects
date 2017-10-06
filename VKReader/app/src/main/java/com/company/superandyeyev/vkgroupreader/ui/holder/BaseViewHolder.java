package com.company.superandyeyev.vkgroupreader.ui.holder;

import android.support.v7.widget.RecyclerView;

import android.view.View;

import com.company.superandyeyev.vkgroupreader.model.view.BaseViewModel;

/**
 * Created by DIMON on 30.09.2017.
 */

public abstract class BaseViewHolder<Item extends BaseViewModel> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewHolder(Item item);

    public abstract void unbindViewHolder();
}
