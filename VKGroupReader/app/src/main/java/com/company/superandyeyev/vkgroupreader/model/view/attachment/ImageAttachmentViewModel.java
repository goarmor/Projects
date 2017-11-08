package com.company.superandyeyev.vkgroupreader.model.view.attachment;

import android.view.View;

import com.company.superandyeyev.vkgroupreader.model.attachment.Photo;
import com.company.superandyeyev.vkgroupreader.model.view.BaseViewModel;
import com.company.superandyeyev.vkgroupreader.ui.view.holder.attachment.ImageAttachmentHolder;

/**
 * Created by DIMON on 29.10.2017.
 */

public class ImageAttachmentViewModel extends BaseViewModel {

    private String mPhotoUrl;
    public boolean needClick = true;

    public ImageAttachmentViewModel(String url) {
        mPhotoUrl = url;

        needClick = false;
    }

    public ImageAttachmentViewModel(Photo photo) {
        mPhotoUrl = photo.getPhoto604();
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentImage;
    }

    @Override
    public ImageAttachmentHolder onCreateViewHolder(View view) {
        return new ImageAttachmentHolder(view);
    }


    public String getPhotoUrl() {
        return mPhotoUrl;
    }
}
