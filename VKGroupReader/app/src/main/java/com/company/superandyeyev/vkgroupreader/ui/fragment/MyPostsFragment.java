package com.company.superandyeyev.vkgroupreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.company.superandyeyev.vkgroupreader.R;

/**
 * Created by DIMON on 05.10.2017.
 */

public class MyPostsFragment extends NewsFeedFragment {

    public MyPostsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.setEnableIdFiltering(true);
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_my_posts;
    }
}
