package com.company.superandyeyev.vkgroupreader.ui.view.holder.attachment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.company.superandyeyev.vkgroupreader.R;
import com.company.superandyeyev.vkgroupreader.common.utils.Utils;
import com.company.superandyeyev.vkgroupreader.model.view.attachment.LinkExternalViewModel;
import com.company.superandyeyev.vkgroupreader.ui.view.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DIMON on 29.10.2017.
 */

public class LinkExternalAttachmentHolder extends BaseViewHolder<LinkExternalViewModel> {

    @BindView(R.id.iv_attachment_picture)
    public ImageView image;

    @BindView(R.id.tv_title)
    public TextView title;

    @BindView(R.id.tv_attachment_url)
    public TextView url;

    public LinkExternalAttachmentHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(LinkExternalViewModel linkExternalViewModel) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.openUrlInActionView(linkExternalViewModel.getUrl(), view.getContext());
            }
        });
        title.setText(linkExternalViewModel.getTitle());
        url.setText(linkExternalViewModel.getUrl());

        Glide.with(itemView.getContext()).load(linkExternalViewModel.getImageUrl()).into(image);
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        title.setText(null);
        title.setText(null);

        image.setImageBitmap(null);
    }

}