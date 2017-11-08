package com.company.superandyeyev.vkgroupreader.model.view;

import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.company.superandyeyev.vkgroupreader.MyApplication;
import com.company.superandyeyev.vkgroupreader.R;
import com.company.superandyeyev.vkgroupreader.common.manager.MyFragmentManager;
import com.company.superandyeyev.vkgroupreader.ui.activity.BaseActivity;
import com.company.superandyeyev.vkgroupreader.ui.fragment.InfoContactsFragment;
import com.company.superandyeyev.vkgroupreader.ui.view.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DIMON on 27.10.2017.
 */

public class InfoContactsViewModel extends BaseViewModel{
    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoContacts;
    }

    @Override
    public InfoContactsViewHolder onCreateViewHolder(View view) {
        return new InfoContactsViewHolder(view);
    }

    public static class InfoContactsViewHolder extends BaseViewHolder<InfoContactsViewModel> {

        @Inject
        MyFragmentManager mFragmentManager;

        @BindView(R.id.rv_contacts)
        RelativeLayout rvContacts;

        public InfoContactsViewHolder(View itemView) {
            super(itemView);
            MyApplication.getApplicationComponent().inject(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoContactsViewModel infoContactsViewModel) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("CLICK_LINK", "click to InfoLinksViewModel");
                    mFragmentManager.addFragment((BaseActivity) view.getContext(), new InfoContactsFragment(),
                            R.id.main_wrapper);



                }
            });
        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
