package com.company.superandyeyev.vkgroupreader.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.company.superandyeyev.vkgroupreader.MyApplication;
import com.company.superandyeyev.vkgroupreader.common.utils.VkListHelper;
import com.company.superandyeyev.vkgroupreader.model.CommentItem;
import com.company.superandyeyev.vkgroupreader.model.view.BaseViewModel;
import com.company.superandyeyev.vkgroupreader.model.view.CommentFooterViewModel;
import com.company.superandyeyev.vkgroupreader.model.view.OpenedPostHeaderViewModel;
import com.company.superandyeyev.vkgroupreader.mvp.view.BaseFeedView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by DIMON on 30.10.2017.
 */

@InjectViewState
public class OpenedCommentPresenter extends BaseFeedPresenter<BaseFeedView> {

    int id;

    public OpenedCommentPresenter() {
        MyApplication.getApplicationComponent().inject(this);

    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return createObservable();

    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return createObservable();
    }


    private Observable<BaseViewModel> createObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .retry(2)
                .flatMap(commentItem -> {
                    List<BaseViewModel> list = new ArrayList<>();
                    List<BaseViewModel> forwardedList = new ArrayList<>();

                    list.add(new OpenedPostHeaderViewModel(commentItem));

                    list.addAll(VkListHelper.getAttachmentVkItems(commentItem.getAttachments()));

                    list.add(new CommentFooterViewModel(commentItem));

                    return Observable.fromIterable(list).concatWith(Observable.fromIterable(forwardedList));
                });
    }



    public Callable<CommentItem> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            CommentItem commentItem = realm.where(CommentItem.class).equalTo("id", id).findFirst();

            return realm.copyFromRealm(commentItem);
        };
    }

    public void setId(int id) {
        this.id = id;
    }
}