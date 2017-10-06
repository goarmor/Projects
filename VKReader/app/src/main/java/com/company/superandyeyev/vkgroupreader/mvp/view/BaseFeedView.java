package com.company.superandyeyev.vkgroupreader.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.company.superandyeyev.vkgroupreader.model.view.BaseViewModel;

import java.util.List;

/**
 * Created by DIMON on 03.10.2017.
 */

public interface BaseFeedView extends MvpView {
    void showRefreshing();

    void hideRefreshing();


    void showListProgress();

    void hideListProgress();


    void showError(String message);


    void setItems(List<BaseViewModel> items);

    void addItems(List<BaseViewModel> items);
}
