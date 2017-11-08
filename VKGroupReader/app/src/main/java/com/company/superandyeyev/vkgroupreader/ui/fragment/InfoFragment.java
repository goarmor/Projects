package com.company.superandyeyev.vkgroupreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.company.superandyeyev.vkgroupreader.R;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.BaseFeedPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.InfoPresenter;

import butterknife.ButterKnife;

/**
 * Created by DIMON on 28.10.2017.
 */

public class InfoFragment extends  BaseFeedFragment {

    @InjectPresenter
    InfoPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setWithEndlessList(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_info;
    }
}

