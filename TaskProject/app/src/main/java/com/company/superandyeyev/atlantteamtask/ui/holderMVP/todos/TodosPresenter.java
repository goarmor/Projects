package com.company.superandyeyev.atlantteamtask.ui.holderMVP.todos;

import com.company.superandyeyev.atlantteamtask.repository.Repository;
import com.company.superandyeyev.atlantteamtask.repository.RepositoryProvider;

/**
 * Created by DIMON on 13.10.2017.
 */

public class TodosPresenter {

    TodosView todosView;

    Repository repository;

    public TodosPresenter(TodosView todosView) {
        this.todosView = todosView;
        repository = RepositoryProvider.getRepository();
    }


    public void downloadRandomTodo() {
        repository.getTodo()
                .doOnSubscribe(disposable -> {
                    todosView.showLoading();
                })
                .doFinally(todosView::hideLoading)
                .subscribe(todo -> todosView.initTodo(todo),
                        throwable -> todosView.showDownloadError());
    }

}
