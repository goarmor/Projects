package com.company.superandyeyev.vkgroupreader.model.attachment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.model.VKAttachments;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Link extends RealmObject implements Attachment {

    @PrimaryKey
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("caption")
    @Expose
    public String caption;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("photo")
    @Expose
    public Photo photo;
    @SerializedName("is_external")
    @Expose
    public int isExternal;



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public int getIsExternal() {
        return isExternal;
    }

    public void setIsExternal(int isExternal) {
        this.isExternal = isExternal;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getType() {
        return VKAttachments.TYPE_LINK;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
