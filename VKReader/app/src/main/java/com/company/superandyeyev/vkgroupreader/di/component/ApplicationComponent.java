package com.company.superandyeyev.vkgroupreader.di.component;

import com.company.superandyeyev.vkgroupreader.common.manager.NetworkManager;
import com.company.superandyeyev.vkgroupreader.di.module.ApplicationModule;
import com.company.superandyeyev.vkgroupreader.di.module.ManagerModule;
import com.company.superandyeyev.vkgroupreader.di.module.RestModule;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.MainPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.MembersPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.NewsFeedPresenter;
import com.company.superandyeyev.vkgroupreader.ui.activity.BaseActivity;
import com.company.superandyeyev.vkgroupreader.ui.activity.MainActivity;
import com.company.superandyeyev.vkgroupreader.ui.fragment.NewsFeedFragment;
import com.company.superandyeyev.vkgroupreader.ui.holder.NewsItemBodyHolder;
import com.company.superandyeyev.vkgroupreader.ui.holder.NewsItemFooterHolder;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DIMON on 29.09.2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity activity);
    void inject(MainActivity activity);

    void inject(NewsFeedFragment fragment);

    void inject(NewsItemBodyHolder holder);
    void inject(NewsItemFooterHolder holder);

    void inject(NewsFeedPresenter presenter);
    void inject(MainPresenter mainPresenter);
    void inject(MembersPresenter presenter);

    void inject(NetworkManager manager);
}
