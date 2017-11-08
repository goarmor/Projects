package com.company.superandyeyev.vkgroupreader.model.view;

import android.view.View;
import android.widget.TextView;

import com.company.superandyeyev.vkgroupreader.MyApplication;
import com.company.superandyeyev.vkgroupreader.R;
import com.company.superandyeyev.vkgroupreader.common.manager.MyFragmentManager;
import com.company.superandyeyev.vkgroupreader.model.Place;
import com.company.superandyeyev.vkgroupreader.model.Topic;
import com.company.superandyeyev.vkgroupreader.ui.activity.BaseActivity;
import com.company.superandyeyev.vkgroupreader.ui.fragment.TopicCommentsFragment;
import com.company.superandyeyev.vkgroupreader.ui.view.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DIMON on 27.10.2017.
 */

public class TopicViewModel extends  BaseViewModel{

    private int mid;
    private int mGroupid;
    private String mTitle;
    private String mCommentsCount;

    public TopicViewModel() {
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.Topic;
    }

    public TopicViewModel(Topic topic) {
        this.mid = topic.getId();
        this.mGroupid = topic.getGroupid();
        this.mTitle = topic.getTitle();
        this.mCommentsCount = topic.getComments() + " сообщений";
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new TopicViewHolder(view);
    }

    public int getId() {
        return mid;
    }

    public int getGroupId() {
        return mGroupid;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getCommentsCount() {
        return mCommentsCount;
    }

    public void setId(int mid) {
        this.mid = mid;
    }

    public void setGroupId(int mGroupid) {
        this.mGroupid = mGroupid;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setCommentsCount(String mCommentsCount) {
        this.mCommentsCount = mCommentsCount;
    }

    public static class TopicViewHolder extends BaseViewHolder<TopicViewModel> {

        @Inject
        MyFragmentManager mFragmentManager;

        @BindView(R.id.tv_title)
        public TextView tvTitle;

        @BindView(R.id.tv_comments_count)
        public TextView tvCommentsCount;

        public TopicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            MyApplication.getApplicationComponent().inject(this);
        }

        @Override
        public void bindViewHolder(TopicViewModel topicViewModel) {
            tvTitle.setText(topicViewModel.getTitle());
            tvCommentsCount.setText(topicViewModel.getCommentsCount());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFragmentManager.addFragment((BaseActivity) view.getContext(),
                            TopicCommentsFragment.newInstance(new Place(String.valueOf(topicViewModel.getGroupId()), String.valueOf(topicViewModel.getId()))),
                            R.id.main_wrapper);
                }
            });
        }

        @Override
        public void unbindViewHolder() {
            tvTitle.setText(null);
            tvCommentsCount.setText(null);

        }
    }
}
