package com.company.superandyeyev.vkgroupreader.di.module;

import com.company.superandyeyev.vkgroupreader.common.manager.MyFragmentManager;
import com.company.superandyeyev.vkgroupreader.common.manager.NetworkManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DIMON on 29.09.2017.
 */

@Module
public class ManagerModule {
    @Provides
    @Singleton
    MyFragmentManager provideMyFragmentManager() {
        return new MyFragmentManager();
    }

    @Provides
    @Singleton
    NetworkManager provideNetworkManager() {
        return new NetworkManager();
    }
}
