package com.company.superandyeyev.vkgroupreader.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.company.superandyeyev.vkgroupreader.CurrentUser;
import com.company.superandyeyev.vkgroupreader.MyApplication;
import com.company.superandyeyev.vkgroupreader.common.utils.VkListHelper;
import com.company.superandyeyev.vkgroupreader.consts.ApiConstants;
import com.company.superandyeyev.vkgroupreader.model.WallItem;
import com.company.superandyeyev.vkgroupreader.model.view.BaseViewModel;
import com.company.superandyeyev.vkgroupreader.model.view.NewsItemBodyViewModel;
import com.company.superandyeyev.vkgroupreader.model.view.NewsItemFooterViewModel;
import com.company.superandyeyev.vkgroupreader.model.view.NewsItemHeaderViewModel;
import com.company.superandyeyev.vkgroupreader.mvp.view.BaseFeedView;
import com.company.superandyeyev.vkgroupreader.rest.api.WallApi;
import com.company.superandyeyev.vkgroupreader.rest.model.request.WallGetRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by DIMON on 03.10.2017.
 */

@InjectViewState
public class NewsFeedPresenter extends BaseFeedPresenter<BaseFeedView>{

    private boolean enableIdFiltering = false;

    @Inject
    WallApi mWallApi;

    public NewsFeedPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mWallApi.get(new WallGetRequestModel(ApiConstants.MY_GROUP_ID, count, offset).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .compose(applyFilter())
                .doOnNext(this::saveToDb)
                .flatMap(wallitem -> {
                    List<BaseViewModel> baseItems = new ArrayList<>();
                    baseItems.add(new NewsItemHeaderViewModel(wallitem));
                    baseItems.add(new NewsItemBodyViewModel(wallitem));
                    baseItems.add(new NewsItemFooterViewModel(wallitem));
                    return Observable.fromIterable(baseItems);
                });
    }

    public void setEnableIdFiltering(boolean enableIdFiltering) {
        this.enableIdFiltering = enableIdFiltering;
    }

    protected ObservableTransformer<WallItem, WallItem> applyFilter() {
        if (enableIdFiltering && CurrentUser.getId() != null) {
            return baseItemObservable -> baseItemObservable.filter(
                    wallItem -> CurrentUser.getId().equals(String.valueOf(wallItem.getFromId()))
            );
        } else {
            return baseItemObservable -> baseItemObservable;
        }
    }

    public Callable<List<WallItem>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {"date"};
            Sort[] sortOrder = {Sort.DESCENDING};
            Realm realm = Realm.getDefaultInstance();
            RealmResults<WallItem> realmResults = realm.where(WallItem.class)
                    .findAllSorted(sortFields, sortOrder);
            return realm.copyFromRealm(realmResults);
        };
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .compose(applyFilter())
                .flatMap(wallItem -> Observable.fromIterable(parsePojoModel(wallItem)));
    }

    private List<BaseViewModel> parsePojoModel(WallItem wallItem) {
        List<BaseViewModel> baseItems = new ArrayList<>();
        baseItems.add(new NewsItemHeaderViewModel(wallItem));
        baseItems.add(new NewsItemBodyViewModel(wallItem));
        baseItems.add(new NewsItemFooterViewModel(wallItem));

        return baseItems;
    }
}
