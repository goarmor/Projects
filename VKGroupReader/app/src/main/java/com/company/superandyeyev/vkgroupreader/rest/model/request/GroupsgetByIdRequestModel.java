package com.company.superandyeyev.vkgroupreader.rest.model.request;

import com.company.superandyeyev.vkgroupreader.consts.ApiConstants;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

/**
 * Created by DIMON on 27.10.2017.
 */

public class GroupsgetByIdRequestModel extends BaseRequestModel{

    @SerializedName(VKApiConst.GROUP_ID)
    int groupid;

    @SerializedName(VKApiConst.FIELDS)
    String fields = ApiConstants.DEFAULT_GROUP_FIELDS;

    public GroupsgetByIdRequestModel(int groupid) {
        this.groupid = Math.abs(groupid);
    }

    public int getGroupid() {
        return groupid;
    }

    public String getFields() {
        return fields;
    }

    public void setGroupid(int groupid) {
        this.groupid = Math.abs(groupid);
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(VKApiConst.GROUP_ID, String.valueOf(getGroupid()));
        map.put(VKApiConst.FIELDS, String.valueOf(getFields()));

    }
}
