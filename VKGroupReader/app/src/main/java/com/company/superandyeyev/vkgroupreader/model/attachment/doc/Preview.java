package com.company.superandyeyev.vkgroupreader.model.attachment.doc;

import com.company.superandyeyev.vkgroupreader.model.attachment.video.Video;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by DIMON on 28.10.2017.
 */

public class Preview extends RealmObject {
    @SerializedName("photo")
    @Expose
    public PhotoPreview photo;
    @SerializedName("video")
    @Expose
    public Video video;


    public PhotoPreview getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoPreview photo) {
        this.photo = photo;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

}
