package com.company.superandyeyev.vkgroupreader.model.view;

import android.view.View;

import com.company.superandyeyev.vkgroupreader.model.WallItem;
import com.company.superandyeyev.vkgroupreader.model.view.counter.CommentCounterViewModel;
import com.company.superandyeyev.vkgroupreader.model.view.counter.LikeCounterViewModel;
import com.company.superandyeyev.vkgroupreader.model.view.counter.RepostCounterViewModel;
import com.company.superandyeyev.vkgroupreader.ui.holder.BaseViewHolder;
import com.company.superandyeyev.vkgroupreader.ui.holder.NewsItemFooterHolder;

/**
 * Created by DIMON on 02.10.2017.
 */

public class NewsItemFooterViewModel extends BaseViewModel {

    private int mId;
    private int ownerId;

    private long mDateLong;

    private LikeCounterViewModel mLikes;
    private CommentCounterViewModel mComments;
    private RepostCounterViewModel mReposts;


    public NewsItemFooterViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();
        this.ownerId = wallItem.getOwnerId();

        this.mDateLong = wallItem.getDate();

        this.mLikes = new LikeCounterViewModel(wallItem.getLikes());
        this.mComments = new CommentCounterViewModel(wallItem.getComments());
        this.mReposts = new RepostCounterViewModel(wallItem.getReposts());
    }

    @Override
    public BaseViewModel.LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemFooter;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemFooterHolder(view);
    }


    @Override
    public boolean isItemDecorator() {
        return true;
    }

    public int getId() {
        return mId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public long getDateLong() {
        return mDateLong;
    }

    public LikeCounterViewModel getLikes() {
        return mLikes;
    }

    public CommentCounterViewModel getComments() {
        return mComments;
    }

    public RepostCounterViewModel getReposts() {
        return mReposts;
    }

    public void setmLikes(LikeCounterViewModel mLikes) {
        this.mLikes = mLikes;
    }
}

