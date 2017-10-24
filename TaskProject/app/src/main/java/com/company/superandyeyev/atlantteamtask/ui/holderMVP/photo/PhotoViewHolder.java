package com.company.superandyeyev.atlantteamtask.ui.holderMVP.photo;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.company.superandyeyev.atlantteamtask.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * Created by DIMON on 10.10.2017.
 */

public class PhotoViewHolder extends RecyclerView.ViewHolder implements PhotoView {

    @BindView(R.id.photo_image)
    ImageView imageView;

    @BindView(R.id.photo_progressBar)
    ProgressBar progressBar;

    private PhotoPresenter photoPresenter;

    public PhotoViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        photoPresenter = new PhotoPresenter(this);

        photoPresenter.init(itemView.getContext());
    }

    @Override
    public void showLoading() {
        imageView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.VISIBLE);
    }


    @Override
    public void showDownloadError() {
        Toasty.error(itemView.getContext(), itemView.getContext().getString(R.string.error_download),
                Toast.LENGTH_SHORT, true).show();
    }


    @Override
    public void showPhoto(Bitmap photo) {
        imageView.setImageBitmap(photo);
    }

}
