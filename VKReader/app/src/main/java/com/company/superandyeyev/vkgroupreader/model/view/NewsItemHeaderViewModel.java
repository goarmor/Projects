package com.company.superandyeyev.vkgroupreader.model.view;

import android.view.View;

import com.company.superandyeyev.vkgroupreader.model.WallItem;
import com.company.superandyeyev.vkgroupreader.ui.holder.BaseViewHolder;
import com.company.superandyeyev.vkgroupreader.ui.holder.NewsItemHeaderHolder;

/**
 * Created by DIMON on 02.10.2017.
 */

public class NewsItemHeaderViewModel extends BaseViewModel {
    private int mId;

    private String mProfilePhoto;
    private String mProfileName;

    private boolean mIsRepost;
    private String mRepostProfileName;


    public NewsItemHeaderViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();

        this.mIsRepost = wallItem.haveSharedRepost();

        if (mIsRepost) {
            this.mRepostProfileName = wallItem.getSharedRepost().senderName;
        }

        this.mProfilePhoto = wallItem.getSenderPhoto();
        this.mProfileName = wallItem.getSenderName();
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.NewsFeedItemHeader;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new NewsItemHeaderHolder(view);
    }


    public int getId() {
        return mId;
    }

    public String getProfilePhoto() {
        return mProfilePhoto;
    }

    public String getProfileName() {
        return mProfileName;
    }

    public boolean isRepost() {
        return mIsRepost;
    }

    public String getRepostProfileName() {
        return mRepostProfileName;
    }
}
