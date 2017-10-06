package com.company.superandyeyev.vkgroupreader.model;

import com.vk.sdk.api.model.Identifiable;

/**
 * Created by DIMON on 02.10.2017.
 */

public interface Owner extends Identifiable {
    String getFullName();

    String getPhoto();
}
