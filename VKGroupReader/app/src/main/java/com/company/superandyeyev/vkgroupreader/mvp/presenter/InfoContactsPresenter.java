package com.company.superandyeyev.vkgroupreader.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.company.superandyeyev.vkgroupreader.MyApplication;
import com.company.superandyeyev.vkgroupreader.consts.ApiConstants;
import com.company.superandyeyev.vkgroupreader.model.Profile;
import com.company.superandyeyev.vkgroupreader.model.view.BaseViewModel;
import com.company.superandyeyev.vkgroupreader.model.view.MemberViewModel;
import com.company.superandyeyev.vkgroupreader.mvp.view.BaseFeedView;
import com.company.superandyeyev.vkgroupreader.rest.api.GroupsApi;
import com.company.superandyeyev.vkgroupreader.rest.api.UsersApi;
import com.company.superandyeyev.vkgroupreader.rest.model.request.GroupsgetByIdRequestModel;
import com.company.superandyeyev.vkgroupreader.rest.model.request.UsersGetRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by DIMON on 31.10.2017.
 */

@InjectViewState
public class InfoContactsPresenter extends BaseFeedPresenter<BaseFeedView>{

    @Inject
    GroupsApi mGroupApi;

    @Inject
    UsersApi mUserApi;

    public InfoContactsPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mGroupApi.getById(new GroupsgetByIdRequestModel(ApiConstants.MY_GROUP_ID).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .flatMap(group-> Observable.fromIterable(group.getContactList()))
                .flatMap(contact -> mUserApi.get(new UsersGetRequestModel(String.valueOf(contact.getUserId())).toMap()))
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(profile -> profile.setContact(true))
                .doOnNext(this::saveToDb)
                .flatMap(profile -> Observable.fromIterable(parsePojoModel(profile)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(profile -> Observable.fromIterable(parsePojoModel(profile)));

    }

    private List<BaseViewModel> parsePojoModel(Profile profile) {
        List<BaseViewModel> items = new ArrayList<>();
        items.add(new MemberViewModel(profile));

        return items;
    }

    private List<BaseViewModel> parsePojoModel(List<Profile> profileList) {
        List<BaseViewModel> items = new ArrayList<>();
        for (Profile profile : profileList) {
            items.addAll(parsePojoModel(profile));
        }

        return items;
    }

    public Callable<List<Profile>> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            List<Profile> result = realm.where(Profile.class)
                    .equalTo("isContact", true)
                    .findAll();
            return realm.copyFromRealm(result);
        };
    }
}