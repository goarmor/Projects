package com.company.superandyeyev.atlantteamtask.ui.holderMVP.todos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.company.superandyeyev.atlantteamtask.R;
import com.company.superandyeyev.atlantteamtask.rest.Model.Todo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * Created by DIMON on 10.10.2017.
 */

public class TodosViewHolder extends RecyclerView.ViewHolder implements TodosView {

    @BindView(R.id.todos_userID_label)
    TextView userId;

    @BindView(R.id.todos_ID_label)
    TextView id;

    @BindView(R.id.todos__task_title_label)
    TextView title;

    @BindView(R.id.todos_completed_label)
    TextView completed;

    @BindView(R.id.todos_progress_bar)
    ProgressBar progressBar;

    private TodosPresenter todosPresenter;

    public TodosViewHolder(final View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        todosPresenter = new TodosPresenter(this);

        todosPresenter.downloadRandomTodo();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void initTodo(Todo todo) {
        userId.setText(itemView.getContext().getString(R.string.user_id) + todo.getUserId().toString());
        id.setText(itemView.getContext().getString(R.string.id) + todo.getId().toString());
        title.setText(itemView.getContext().getString(R.string.title) + todo.getTitle());
        completed.setText(itemView.getContext().getString(R.string.completed) + todo.getCompleted().toString());
    }

    @Override
    public void showDownloadError() {
        userId.setText(itemView.getContext().getString(R.string.user_id) + itemView.getContext().getString(R.string.error_download));
        id.setText(itemView.getContext().getString(R.string.id) + itemView.getContext().getString(R.string.error_download));
        title.setText(itemView.getContext().getString(R.string.title) + itemView.getContext().getString(R.string.error_download));
        completed.setText(itemView.getContext().getString(R.string.completed) + itemView.getContext().getString(R.string.error_download));

        Toasty.error(itemView.getContext(), itemView.getContext().getString(R.string.error_download),
                Toast.LENGTH_SHORT, true).show();
    }


    @OnClick(R.id.todos_action_button)
    public void reloadClick() {
        todosPresenter.downloadRandomTodo();
    }

}
