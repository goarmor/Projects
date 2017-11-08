package com.company.superandyeyev.vkgroupreader.di.module;

import com.company.superandyeyev.vkgroupreader.rest.RestClient;
import com.company.superandyeyev.vkgroupreader.rest.api.AccountApi;
import com.company.superandyeyev.vkgroupreader.rest.api.BoardApi;
import com.company.superandyeyev.vkgroupreader.rest.api.GroupsApi;
import com.company.superandyeyev.vkgroupreader.rest.api.UsersApi;
import com.company.superandyeyev.vkgroupreader.rest.api.VideoApi;
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


    @Provides
    @Singleton
    public RestClient provideRestClient() {
        return mRestClient;
    }

    @Provides
    @Singleton
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
    public GroupsApi provideGroupsApi() {
        return mRestClient.createService(GroupsApi.class);
    }

    @Provides
    @Singleton
    public BoardApi provideBoardApi(){
        return mRestClient.createService(BoardApi.class);
    }

    @Provides
    @Singleton
    public VideoApi provideVideoApi() {
        return mRestClient.createService(VideoApi.class);
    }

    @Provides
    @Singleton
    public AccountApi provideAccountApi() {
        return mRestClient.createService(AccountApi.class);
    }

}
