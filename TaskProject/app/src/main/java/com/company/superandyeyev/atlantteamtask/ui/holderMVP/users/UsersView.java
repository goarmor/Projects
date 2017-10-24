package com.company.superandyeyev.atlantteamtask.ui.holderMVP.users;

import com.company.superandyeyev.atlantteamtask.ui.holderMVP.LoadingView;
import com.company.superandyeyev.atlantteamtask.rest.Model.User;

import java.util.List;

/**
 * Created by DIMON on 13.10.2017.
 */

public interface UsersView extends LoadingView {

    void showUsers(List<User> users);

    void showDownloadError();

}
