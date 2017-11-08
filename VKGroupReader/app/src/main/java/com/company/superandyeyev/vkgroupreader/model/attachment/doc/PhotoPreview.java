package com.company.superandyeyev.vkgroupreader.model.attachment.doc;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by DIMON on 28.10.2017.
 */

public class PhotoPreview extends RealmObject {
    RealmList<Size> sizes;

    public RealmList<Size> getSizes() {
        return sizes;
    }

    public void setSizes(RealmList<Size> sizes) {
        this.sizes = sizes;
    }
}
