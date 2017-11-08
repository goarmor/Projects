package com.company.superandyeyev.vkgroupreader.model.view;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.company.superandyeyev.vkgroupreader.MyApplication;
import com.company.superandyeyev.vkgroupreader.R;
import com.company.superandyeyev.vkgroupreader.common.manager.MyFragmentManager;
import com.company.superandyeyev.vkgroupreader.ui.activity.BaseActivity;
import com.company.superandyeyev.vkgroupreader.ui.fragment.InfoLinksFragment;
import com.company.superandyeyev.vkgroupreader.ui.view.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DIMON on 27.10.2017.
 */

public class InfoLinksViewModel extends BaseViewModel{

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoLinks;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(View view) {
        return new InfoLinkViewHolder(view);
    }

    public static class InfoLinkViewHolder extends BaseViewHolder<InfoLinksViewModel> {

        @Inject
        MyFragmentManager mFragmentManager;

        @BindView(R.id.rv_links)
        RelativeLayout rvLinks;

        public InfoLinkViewHolder(View itemView) {
            super(itemView);
            MyApplication.getApplicationComponent().inject(this);
            ButterKnife.bind(this, itemView);


        }

        @Override
        public void bindViewHolder(InfoLinksViewModel infoLinksViewModel) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("CLICK_LINK", "click to InfoLinksViewModel");
                    mFragmentManager.addFragment((BaseActivity) view.getContext(), new InfoLinksFragment(),
                            R.id.main_wrapper);



                }
            });

        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
