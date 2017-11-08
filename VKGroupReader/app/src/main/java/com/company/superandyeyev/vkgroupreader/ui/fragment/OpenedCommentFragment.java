package com.company.superandyeyev.vkgroupreader.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.company.superandyeyev.vkgroupreader.MyApplication;
import com.company.superandyeyev.vkgroupreader.R;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.BaseFeedPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.OpenedCommentPresenter;

import butterknife.ButterKnife;

/**
 * Created by DIMON on 30.10.2017.
 */

public class OpenedCommentFragment extends BaseFeedFragment {

    @InjectPresenter
    OpenedCommentPresenter mPresenter;

    int id;

    public static OpenedCommentFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt("id", id);
        OpenedCommentFragment fragment = new OpenedCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);

        setWithEndlessList(false);

        if (getArguments() != null) {
            this.id = getArguments().getInt("id");
        }
    }

    @Override
    public void onViewCreated(View view, @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_opened_wall_item;
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        mPresenter.setId(id);
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_opened_comment;
    }



}
