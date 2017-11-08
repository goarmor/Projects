package com.company.superandyeyev.vkgroupreader.model.view.attachment;

import android.view.View;

import com.company.superandyeyev.vkgroupreader.model.attachment.Page;
import com.company.superandyeyev.vkgroupreader.model.view.BaseViewModel;
import com.company.superandyeyev.vkgroupreader.ui.view.holder.attachment.PageAttachmentHolder;

/**
 * Created by DIMON on 29.10.2017.
 */

public class PageAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    public PageAttachmentViewModel(Page page) {
        mUrl = page.getUrl();
        mTitle = page.getTitle();
    }

    public String getTitle() {
        return mTitle;
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentPage;
    }

    @Override
    public PageAttachmentHolder onCreateViewHolder(View view) {
        return new PageAttachmentHolder(view);
    }



    public String getmUrl() {
        return mUrl;
    }
}
