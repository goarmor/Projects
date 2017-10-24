package com.company.superandyeyev.atlantteamtask.ui.holderMVP.photo;

import android.content.Context;

import com.company.superandyeyev.atlantteamtask.repository.Repository;
import com.company.superandyeyev.atlantteamtask.repository.RepositoryProvider;

/**
 * Created by DIMON on 13.10.2017.
 */

public class PhotoPresenter {

    PhotoView photoView;

    Repository repository;

    public PhotoPresenter(PhotoView photoView) {
        this.photoView = photoView;
        repository = RepositoryProvider.getRepository();
    }


    public void init(Context context) {
        repository.getPhoto(context)
                .doOnSubscribe(disposable -> {
                    photoView.showLoading();
                })
                .doFinally(photoView::hideLoading)
                .subscribe(bitmap -> photoView.showPhoto(bitmap),
                        throwable -> photoView.showDownloadError());
    }


}
