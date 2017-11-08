package com.company.superandyeyev.vkgroupreader.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.company.superandyeyev.vkgroupreader.MyApplication;
import com.company.superandyeyev.vkgroupreader.consts.ApiConstants;
import com.company.superandyeyev.vkgroupreader.model.Member;
import com.company.superandyeyev.vkgroupreader.model.Topic;
import com.company.superandyeyev.vkgroupreader.model.view.BaseViewModel;
import com.company.superandyeyev.vkgroupreader.model.view.TopicViewModel;
import com.company.superandyeyev.vkgroupreader.mvp.view.BaseFeedView;
import com.company.superandyeyev.vkgroupreader.rest.api.BoardApi;
import com.company.superandyeyev.vkgroupreader.rest.model.request.BoardGetTopicsRequestModel;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by DIMON on 27.10.2017.
 */

@InjectViewState
public class BoardPresenter extends BaseFeedPresenter<BaseFeedView>{

    @Inject
    BoardApi mBoardApi;

    public BoardPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mBoardApi.getTopics(new BoardGetTopicsRequestModel(
                ApiConstants.MY_GROUP_ID, count, offset
        ).toMap())
                .flatMap(baseItemResponseFull ->
                        Observable.fromIterable(baseItemResponseFull.response.getItems()))
                .doOnNext(topic -> topic.setGroupid(ApiConstants.MY_GROUP_ID))
                .doOnNext(this::saveToDb)
                .map(TopicViewModel::new);
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromrealmCallable())
                .flatMap(Observable::fromIterable)
                .map(TopicViewModel::new);
    }

    public Callable<List<Topic>> getListFromrealmCallable() {
        return () -> {
            String[] sortFields = {Member.ID};
            Sort[] sortOrder = {Sort.DESCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<Topic> results = realm.where(Topic.class)
                    .equalTo("groupId", ApiConstants.MY_GROUP_ID)
                    .findAllSorted(sortFields, sortOrder);

            return realm.copyFromRealm(results);
        };
    }
}
