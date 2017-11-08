package com.company.superandyeyev.vkgroupreader.model.view.attachment;

/**
 * Created by DIMON on 29.10.2017.
 */

import android.view.View;

import com.company.superandyeyev.vkgroupreader.common.utils.Utils;
import com.company.superandyeyev.vkgroupreader.model.attachment.Audio;
import com.company.superandyeyev.vkgroupreader.model.view.BaseViewModel;
import com.company.superandyeyev.vkgroupreader.ui.view.holder.attachment.AudioAttachmentHolder;


public class AudioAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mArtist;

    private String mDuration;


    public AudioAttachmentViewModel(Audio audio) {
        if (audio.getTitle().equals("")) {
            mTitle = "Title";
        } else {
            mTitle = audio.getTitle();
        }

        if (audio.getArtist().equals("")) {
            mArtist = "Various Artist";
        } else {
            mArtist = audio.getArtist();
        }

        mDuration = Utils.parseDuration(audio.getDuration());
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentAudio;
    }

    @Override
    public AudioAttachmentHolder onCreateViewHolder(View view) {
        return new AudioAttachmentHolder(view);
    }


    public String getTitle() {
        return mTitle;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getDuration() {
        return mDuration;
    }
}