package com.company.superandyeyev.atlantteamtask.ui.holderMVP.posts;

import com.company.superandyeyev.atlantteamtask.ui.holderMVP.LoadingView;
import com.company.superandyeyev.atlantteamtask.rest.Model.Post;

/**
 * Created by DIMON on 13.10.2017.
 */

public interface PostsView extends LoadingView {

    void initResult(Post post);

    void showDownloadError();

    void showBondaryError();

    void showResult();

    void hideResult();

    void hideError();

    void clearTextView();

}
