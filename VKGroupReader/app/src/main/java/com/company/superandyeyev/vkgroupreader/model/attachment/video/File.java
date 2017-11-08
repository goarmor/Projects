package com.company.superandyeyev.vkgroupreader.model.attachment.video;

import io.realm.RealmObject;

/**
 * Created by DIMON on 28.10.2017.
 */

public class File extends RealmObject {
    public String external;

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }
}
