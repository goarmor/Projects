package com.company.superandyeyev.vkgroupreader.model.view.counter;

import com.company.superandyeyev.vkgroupreader.model.Likes;

/**
 * Created by DIMON on 02.10.2017.
 */

public class LikeCounterViewModel extends CounterViewModel {
    private Likes mLikes;

    public LikeCounterViewModel(Likes likes) {
        super(likes.getCount());

        this.mLikes = likes;

        if (mLikes.getUserLikes() == 1) {
            setAccentColor();
        }
    }

    public Likes getLikes() {
        return mLikes;
    }
}
