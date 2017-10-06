package com.company.superandyeyev.vkgroupreader.model.view.counter;

import com.company.superandyeyev.vkgroupreader.model.Reposts;

/**
 * Created by DIMON on 02.10.2017.
 */

public class RepostCounterViewModel extends CounterViewModel {

    private Reposts mReposts;

    public RepostCounterViewModel(Reposts reposts) {
        super(reposts.getCount());

        this.mReposts = reposts;
        if (mReposts.getUserReposted() == 1) {
            setAccentColor();
        }
    }

    public Reposts getReposts() {
        return mReposts;
    }
}
