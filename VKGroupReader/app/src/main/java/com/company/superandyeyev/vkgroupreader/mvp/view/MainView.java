package com.company.superandyeyev.vkgroupreader.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.company.superandyeyev.vkgroupreader.model.Profile;
import com.company.superandyeyev.vkgroupreader.ui.fragment.BaseFragment;

/**
 * Created by DIMON on 27.09.2017.
 */

public interface MainView extends MvpView {
    void startSignIn();
    void signedIn();
    void showCurrentUser(Profile profile);
    void showFragmentFromDrawer(BaseFragment baseFragment);
    void startActivityFromDrawer(Class<?> act);
}
