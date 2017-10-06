package com.company.superandyeyev.vkgroupreader.model.view;

import android.view.View;

import com.company.superandyeyev.vkgroupreader.model.WallItem;
import com.company.superandyeyev.vkgroupreader.ui.holder.NewsItemBodyHolder;

/**
 * Created by DIMON on 30.09.2017.
 */

public class NewsItemBodyViewModel extends BaseViewModel {
    private int mId;

    private String mText;
    private String mAttachmentsString;

    private boolean mIsRepost;
    public NewsItemBodyViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();

        this.mIsRepost = wallItem.haveSharedRepost();

        if (mIsRepost) {
            this.mText = wallItem.getSharedRepost().getText();
            this.mAttachmentsString = wallItem.getSharedRepost().getAttachmentsString();
        } else {
            this.mText = wallItem.getText();
            this.mAttachmentsString = wallItem.getAttachmentsString();
        }
    }

    @Override
    public boolean isItemDecorator() {
        return true;
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemBody;
    }

    @Override
    public NewsItemBodyHolder onCreateViewHolder(View view) {
        return new NewsItemBodyHolder(view);
    }


    public String getText() {
        return mText;
    }

    public int getId() {
        return mId;
    }

    public String getAttachmentsString() {
        return mAttachmentsString;
    }
}
