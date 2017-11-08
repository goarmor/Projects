package com.company.superandyeyev.vkgroupreader.model.view.counter;

import com.company.superandyeyev.vkgroupreader.model.countable.Comments;

/**
 * Created by DIMON on 02.10.2017.
 */
public class CommentCounterViewModel extends CounterViewModel{

    private Comments mComments;

    public CommentCounterViewModel(Comments comments) {
        super(comments.getCount());

        this.mComments = comments;
    }

    public Comments getComments() {
        return mComments;
    }
}

