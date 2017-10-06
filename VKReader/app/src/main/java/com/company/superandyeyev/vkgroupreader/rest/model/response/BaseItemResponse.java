package com.company.superandyeyev.vkgroupreader.rest.model.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DIMON on 29.09.2017.
 */

public class BaseItemResponse <T> {
    public Integer count;
    public List<T> items = new ArrayList<>();

    public Integer getCount() {
        return count;
    }

    public List<T> getItems() {
        return items;
    }
}
