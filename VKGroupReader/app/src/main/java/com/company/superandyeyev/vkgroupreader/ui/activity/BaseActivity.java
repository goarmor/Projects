package com.company.superandyeyev.vkgroupreader.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.company.superandyeyev.vkgroupreader.MyApplication;
import com.company.superandyeyev.vkgroupreader.R;
import com.company.superandyeyev.vkgroupreader.common.manager.MyFragmentManager;
import com.company.superandyeyev.vkgroupreader.ui.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DIMON on 28.09.2017.
 */

public abstract class BaseActivity extends MvpAppCompatActivity {

    @BindView(R.id.progress)
    protected ProgressBar mProgressBar;

    @BindView(R.id.main_wrapper)
    FrameLayout mParent;

    @Inject
    MyFragmentManager myFragmentManager;

    @Inject
    LayoutInflater mLayoutInflater;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    public FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ButterKnife.bind(this);

        MyApplication.getApplicationComponent().inject(this);

        setSupportActionBar(toolbar);

        mLayoutInflater.inflate(getMainContentLayout(), mParent);
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    @LayoutRes
    protected abstract int getMainContentLayout();


    public void fragmentOnScreen(BaseFragment baseFragment) {
        setToolbarTitle(baseFragment.createToolbarTitle(this));
        setupFabVisibility(baseFragment.needFab());
    }


    private void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void setupFabVisibility(boolean needFab) {
        if (mFab == null) return;

        if (needFab) {
            mFab.show();
        } else {
            mFab.hide();
        }
    }


    public void setContent(BaseFragment fragment) {
        myFragmentManager.setFragment(this, fragment, R.id.main_wrapper);
    }

    public void addContent(BaseFragment fragment) {
        myFragmentManager.addFragment(this, fragment, R.id.main_wrapper);
    }

    public boolean removeCurrentFragment() {
        return myFragmentManager.removeCurrentFragment(this);
    }

    public boolean removeFragment(BaseFragment fragment) {
        return myFragmentManager.removeFragment(this, fragment);
    }


    @Override
    public void onBackPressed() {
        removeCurrentFragment();
    }
}