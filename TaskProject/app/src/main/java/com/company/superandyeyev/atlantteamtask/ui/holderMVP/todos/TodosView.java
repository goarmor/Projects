package com.company.superandyeyev.atlantteamtask.ui.holderMVP.todos;

import com.company.superandyeyev.atlantteamtask.ui.holderMVP.LoadingView;
import com.company.superandyeyev.atlantteamtask.rest.Model.Todo;

/**
 * Created by DIMON on 13.10.2017.
 */

public interface TodosView extends LoadingView {

    void initTodo(Todo todo);

    void showDownloadError();

}
