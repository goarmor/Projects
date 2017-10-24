package com.company.superandyeyev.atlantteamtask.ui.holderMVP.users;

import com.company.superandyeyev.atlantteamtask.repository.Repository;
import com.company.superandyeyev.atlantteamtask.repository.RepositoryProvider;

/**
 * Created by DIMON on 13.10.2017.
 */

public class UsersPresenter {

    private UsersView usersView;

    private Repository repository;

    public UsersPresenter(UsersView usersView) {
        repository = RepositoryProvider.getRepository();
        this.usersView = usersView;
    }


    public void init() {
       repository.getUsers()
                .doOnSubscribe(disposable -> {
                    usersView.showLoading();
                })
                .doFinally(usersView::hideLoading)
                .subscribe(users -> usersView.showUsers(users),
                        throwable -> usersView.showDownloadError());
    }

}
