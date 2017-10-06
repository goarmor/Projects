package com.company.superandyeyev.vkgroupreader.rest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DIMON on 29.09.2017.
 */

public class Full<T> {
    @SerializedName("response")
    @Expose
    public T response;
}
