package com.company.superandyeyev.vkgroupreader.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.company.superandyeyev.vkgroupreader.model.WallItem;
import com.company.superandyeyev.vkgroupreader.model.view.counter.LikeCounterViewModel;

/**
 * Created by DIMON on 29.10.2017.
 */

public interface PostFooterView extends MvpView {
    void like(LikeCounterViewModel likes);

    void openComments(WallItem wallItem);
}
