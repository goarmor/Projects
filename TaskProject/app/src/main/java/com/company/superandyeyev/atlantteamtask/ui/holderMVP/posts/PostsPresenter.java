package com.company.superandyeyev.atlantteamtask.ui.holderMVP.posts;

import com.company.superandyeyev.atlantteamtask.repository.Repository;
import com.company.superandyeyev.atlantteamtask.repository.RepositoryProvider;

/**
 * Created by DIMON on 13.10.2017.
 */

public class PostsPresenter {

    private PostsView postsView;

    private Repository repository;

    public PostsPresenter(PostsView postsView) {
        repository = RepositoryProvider.getRepository();
        this.postsView = postsView;
    }


    public void postLoad(Integer n) {
        postsView.hideError();
        if (n != null) {
            if ((n >= 1) && (n <= 100)) {
                repository.getPost(n)
                        .doOnSubscribe(disposable -> {
                            postsView.showLoading();
                        })
                        .doFinally(postsView::hideLoading)
                        .subscribe(post -> postsView.initResult(post),
                                throwable -> postsView.showDownloadError());
            } else {
                postsView.showBondaryError();
            }
        } else {
            postsView.showBondaryError();
        }
    }

    public void showResult() {
        postsView.showResult();
    }

    public void hideResult() {
        postsView.hideResult();
    }

    public void clearText() {postsView.clearTextView(); }
}
