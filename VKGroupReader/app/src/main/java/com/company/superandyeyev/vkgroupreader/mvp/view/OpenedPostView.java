package com.company.superandyeyev.vkgroupreader.mvp.view;

import com.company.superandyeyev.vkgroupreader.model.view.NewsItemFooterViewModel;

/**
 * Created by DIMON on 29.10.2017.
 */

public interface OpenedPostView extends BaseFeedView {
    void setFooter(NewsItemFooterViewModel viewModel);
}
