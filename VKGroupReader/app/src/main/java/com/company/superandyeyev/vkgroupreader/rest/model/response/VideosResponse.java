package com.company.superandyeyev.vkgroupreader.rest.model.response;

import com.company.superandyeyev.vkgroupreader.model.attachment.video.Video;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DIMON on 29.10.2017.
 */

public class VideosResponse {
    public int count;
    @SerializedName("items")
    @Expose
    public List<Video> items;
}
