package com.company.superandyeyev.vkgroupreader.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.company.superandyeyev.vkgroupreader.MyApplication;
import com.company.superandyeyev.vkgroupreader.R;
import com.company.superandyeyev.vkgroupreader.model.Place;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.BaseFeedPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.TopicCommentsPresenter;

import butterknife.ButterKnife;

/**
 * Created by DIMON on 30.10.2017.
 */

public class TopicCommentsFragment extends BaseFeedFragment {

    public static TopicCommentsFragment newInstance(Place place) {

        Bundle args = new Bundle();
        args.putAll(place.toBundle());

        TopicCommentsFragment fragment = new TopicCommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @InjectPresenter
    TopicCommentsPresenter mPresenter;


    Place mPlace;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);
        setWithEndlessList(true);

        mPlace = new Place(getArguments());
    }

    @Override
    public void onViewCreated(View view, @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }


    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        mPresenter.setPlace(mPlace);
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_comments;
    }
}
