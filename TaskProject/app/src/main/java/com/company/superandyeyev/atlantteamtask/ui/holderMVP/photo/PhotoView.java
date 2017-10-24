package com.company.superandyeyev.atlantteamtask.ui.holderMVP.photo;

import android.graphics.Bitmap;

import com.company.superandyeyev.atlantteamtask.ui.holderMVP.LoadingView;

/**
 * Created by DIMON on 13.10.2017.
 */

public interface PhotoView extends LoadingView {

    void showPhoto(Bitmap photo);

    void showDownloadError();

}
