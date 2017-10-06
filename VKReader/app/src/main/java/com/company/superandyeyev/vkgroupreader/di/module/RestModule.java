package com.company.superandyeyev.vkgroupreader.di.module;

import com.company.superandyeyev.vkgroupreader.rest.RestClient;
import com.company.superandyeyev.vkgroupreader.rest.api.GroupsApi;
import com.company.superandyeyev.vkgroupreader.rest.api.UsersApi;
import com.company.superandyeyev.vkgroupreader.rest.api.WallApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DIMON on 29.09.2017.
 */

@Module
public class RestModule {

    private RestClient mRestClient;

    public RestModule() {
        mRestClient = new RestClient();
    }


    @Singleton
    @Provides
    public RestClient provideRestClient() {
        return mRestClient;
    }


    @Singleton
    @Provides
    public WallApi provideWallApi() {
        return mRestClient.createService(WallApi.class);
    }


    @Provides
    @Singleton
    public UsersApi provideUsersApi() {
        return mRestClient.createService(UsersApi.class);
    }


    @Provides
    @Singleton
    public GroupsApi provideGroupsApi() {return mRestClient.createService(GroupsApi.class); }
}
