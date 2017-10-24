package com.company.superandyeyev.atlantteamtask.ui.holderMVP.users;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.company.superandyeyev.atlantteamtask.R;
import com.company.superandyeyev.atlantteamtask.rest.Model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * Created by DIMON on 10.10.2017.
 */

public class UsersViewHolder extends RecyclerView.ViewHolder implements UsersView {

    @BindView(R.id.users_user_1_text)
    TextView userName1;

    @BindView(R.id.users_user_2_text)
    TextView userName2;

    @BindView(R.id.users_user_3_text)
    TextView userName3;

    @BindView(R.id.users_user_4_text)
    TextView userName4;

    @BindView(R.id.users_user_5_text)
    TextView userName5;

    @BindView(R.id.users_progressBar)
    ProgressBar progressBar;

    private UsersPresenter usersPresenter;

    public UsersViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        usersPresenter = new UsersPresenter(this);

        usersPresenter.init();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        userName1.setVisibility(View.INVISIBLE);
        userName2.setVisibility(View.INVISIBLE);
        userName3.setVisibility(View.INVISIBLE);
        userName4.setVisibility(View.INVISIBLE);
        userName5.setVisibility(View.INVISIBLE);
    }


    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        userName1.setVisibility(View.VISIBLE);
        userName2.setVisibility(View.VISIBLE);
        userName3.setVisibility(View.VISIBLE);
        userName4.setVisibility(View.VISIBLE);
        userName5.setVisibility(View.VISIBLE);
    }


    @Override
    public void showDownloadError() {
        userName1.setText(itemView.getContext().getString(R.string.user_1) + itemView.getContext().getString(R.string.error_download));
        userName2.setText(itemView.getContext().getString(R.string.user_2) + itemView.getContext().getString(R.string.error_download));
        userName3.setText(itemView.getContext().getString(R.string.user_3) + itemView.getContext().getString(R.string.error_download));
        userName4.setText(itemView.getContext().getString(R.string.user_4) + itemView.getContext().getString(R.string.error_download));
        userName5.setText(itemView.getContext().getString(R.string.user_5) + itemView.getContext().getString(R.string.error_download));
        Toasty.error(itemView.getContext(), itemView.getContext().getString(R.string.error_download),
                Toast.LENGTH_SHORT, true).show();
    }


    @Override
    public void showUsers(List<User> users) {
        userName1.setText(itemView.getContext().getString(R.string.user_1) + users.get(0).getName());
        userName2.setText(itemView.getContext().getString(R.string.user_2) + users.get(1).getName());
        userName3.setText(itemView.getContext().getString(R.string.user_3) + users.get(2).getName());
        userName4.setText(itemView.getContext().getString(R.string.user_4) + users.get(3).getName());
        userName5.setText(itemView.getContext().getString(R.string.user_5) + users.get(4).getName());
    }


}
