package com.company.superandyeyev.atlantteamtask.ui.holderMVP.сomments;

import com.company.superandyeyev.atlantteamtask.repository.Repository;
import com.company.superandyeyev.atlantteamtask.repository.RepositoryProvider;

/**
 * Created by DIMON on 13.10.2017.
 */

public class CommentsPresenter {

    private CommentsView commentsView;

    private Repository repository;

    public CommentsPresenter(CommentsView commentsView) {
        repository = RepositoryProvider.getRepository();
        this.commentsView = commentsView;
    }

    public void commentLoad(Integer n) {
        commentsView.hideError();
        if (n != null) {
            if ((n >= 1) && (n <= 500)) {
                repository.getComment(n)
                        .doOnSubscribe(disposable -> {
                            commentsView.showLoading();
                        })
                        .doFinally(commentsView::hideLoading)
                        .subscribe(comment -> commentsView.initResult(comment),
                                throwable -> commentsView.showDownloadError());

            } else {
                commentsView.showBondaryError();
            }
        } else {commentsView.showBondaryError();}
    }


    public void showResult() {
        commentsView.showResult();
    }

    public void hideResult() {
        commentsView.hideResult();
    }

    public void clearText() {commentsView.clearTextView(); }
}
