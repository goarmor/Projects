package com.company.superandyeyev.vkgroupreader.ui.view.holder.attachment;

import android.view.View;
import android.widget.TextView;

import com.company.superandyeyev.vkgroupreader.R;
import com.company.superandyeyev.vkgroupreader.common.utils.Utils;
import com.company.superandyeyev.vkgroupreader.model.view.attachment.PageAttachmentViewModel;
import com.company.superandyeyev.vkgroupreader.ui.view.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DIMON on 29.10.2017.
 */

public class PageAttachmentHolder extends BaseViewHolder<PageAttachmentViewModel> {
    @BindView(R.id.tv_title)
    public TextView title;

    public PageAttachmentHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(PageAttachmentViewModel pageAttachmentViewModel) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.openUrlInActionView(pageAttachmentViewModel.getmUrl(), view.getContext());
            }
        });
        title.setText(pageAttachmentViewModel.getTitle());
    }

    @Override
    public void unbindViewHolder() {
        itemView.setOnClickListener(null);
        title.setText(null);
    }


}
