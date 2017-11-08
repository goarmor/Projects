package com.company.superandyeyev.vkgroupreader.di.component;

import com.company.superandyeyev.vkgroupreader.common.manager.NetworkManager;
import com.company.superandyeyev.vkgroupreader.di.module.ApplicationModule;
import com.company.superandyeyev.vkgroupreader.di.module.ManagerModule;
import com.company.superandyeyev.vkgroupreader.di.module.RestModule;
import com.company.superandyeyev.vkgroupreader.model.view.CommentBodyViewModel;
import com.company.superandyeyev.vkgroupreader.model.view.CommentFooterViewModel;
import com.company.superandyeyev.vkgroupreader.model.view.InfoContactsViewModel;
import com.company.superandyeyev.vkgroupreader.model.view.InfoLinksViewModel;
import com.company.superandyeyev.vkgroupreader.model.view.TopicViewModel;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.BoardPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.CommentsPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.InfoContactsPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.InfoLinksPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.InfoPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.MainPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.MembersPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.NewsFeedPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.OpenedCommentPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.OpenedPostPresenter;
import com.company.superandyeyev.vkgroupreader.mvp.presenter.TopicCommentsPresenter;
import com.company.superandyeyev.vkgroupreader.ui.activity.BaseActivity;
import com.company.superandyeyev.vkgroupreader.ui.activity.MainActivity;
import com.company.superandyeyev.vkgroupreader.ui.fragment.CommentsFragment;
import com.company.superandyeyev.vkgroupreader.ui.fragment.InfoContactsFragment;
import com.company.superandyeyev.vkgroupreader.ui.fragment.InfoLinksFragment;
import com.company.superandyeyev.vkgroupreader.ui.fragment.NewsFeedFragment;
import com.company.superandyeyev.vkgroupreader.ui.fragment.OpenedCommentFragment;
import com.company.superandyeyev.vkgroupreader.ui.fragment.OpenedPostFragment;
import com.company.superandyeyev.vkgroupreader.ui.fragment.TopicCommentsFragment;
import com.company.superandyeyev.vkgroupreader.ui.view.holder.NewsItemBodyHolder;
import com.company.superandyeyev.vkgroupreader.ui.view.holder.NewsItemFooterHolder;
import com.company.superandyeyev.vkgroupreader.ui.view.holder.attachment.ImageAttachmentHolder;
import com.company.superandyeyev.vkgroupreader.ui.view.holder.attachment.VideoAttachmentHolder;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by DIMON on 29.09.2017.
 */

@Singleton
@Component(
        modules = {ApplicationModule.class, RestModule.class, ManagerModule.class})
public interface ApplicationComponent {

    //activities
    void inject(BaseActivity activity);
    void inject(MainActivity activity);

    //fragments
    void inject(NewsFeedFragment fragment);
    void inject(OpenedPostFragment fragment);
    void inject(OpenedCommentFragment fragment);
    void inject(CommentsFragment fragment);
    void inject(TopicCommentsFragment fragment);
    void inject(InfoLinksFragment fragment);
    void inject(InfoContactsFragment fragment);

    //holders
    void inject(NewsItemBodyHolder holder);
    void inject(NewsItemFooterHolder holder);
    void inject(ImageAttachmentHolder holder);
    void inject(VideoAttachmentHolder holder);
    void inject(CommentBodyViewModel.CommentBodyViewHolder holder);
    void inject(CommentFooterViewModel.CommentFooterHolder holder);
    void inject(TopicViewModel.TopicViewHolder holder);
    void inject(InfoLinksViewModel.InfoLinkViewHolder holder);
    void inject(InfoContactsViewModel.InfoContactsViewHolder holder);

    //presenters
    void inject(NewsFeedPresenter presenter);
    void inject(MainPresenter presenter);
    void inject(MembersPresenter presenter);
    void inject(BoardPresenter presenter);
    void inject(InfoPresenter presenter);
    void inject(OpenedPostPresenter presenter);
    void inject(CommentsPresenter presenter);
    void inject(OpenedCommentPresenter presenter);
    void inject(TopicCommentsPresenter presenter);
    void inject(InfoLinksPresenter presenter);
    void inject(InfoContactsPresenter presenter);

    //managers
    void inject(NetworkManager manager);


}
