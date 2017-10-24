package com.company.superandyeyev.atlantteamtask.ui.holderMVP.сomments;

import com.company.superandyeyev.atlantteamtask.ui.holderMVP.LoadingView;
import com.company.superandyeyev.atlantteamtask.rest.Model.Comment;

/**
 * Created by DIMON on 13.10.2017.
 */

public interface CommentsView extends LoadingView {

    void initResult(Comment comment);

    void showDownloadError();

    void showBondaryError();

    void showResult();

    void hideResult();

    void hideError();

    void clearTextView();

}
