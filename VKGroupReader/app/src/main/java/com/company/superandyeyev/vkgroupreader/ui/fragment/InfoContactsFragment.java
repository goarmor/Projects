package com.company.superandyeyev.vkgroupreader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.company.superandyeyev.vkgroupreader.MyApplication;
import com.company.superandyeyev.vkgroupreader.R;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.BaseFeedPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.InfoContactsPresenter;

import butterknife.ButterKnife;

/**
 * Created by DIMON on 31.10.2017.
 */

public class InfoContactsFragment extends  BaseFeedFragment {

    @InjectPresenter
    InfoContactsPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setWithEndlessList(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);
        ButterKnife.bind(this, view);
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.title_contacts;
    }
}
