package com.company.superandyeyev.atlantteamtask.ui.holderMVP.posts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.company.superandyeyev.atlantteamtask.R;
import com.company.superandyeyev.atlantteamtask.rest.Model.Post;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * Created by DIMON on 09.10.2017.
 */

public class PostsViewHolder extends RecyclerView.ViewHolder implements PostsView {

    @BindView(R.id.posts_edit_text)
    EditText editText;

    @BindView(R.id.posts_action_button)
    Button confirmButton;

    @BindView(R.id.posts_expand_button)
    ImageButton expandButton;

    @BindView(R.id.posts_userID_label)
    TextView userID;

    @BindView(R.id.posts_error_label)
    TextView errorLabel;

    @BindView(R.id.posts_ID_label)
    TextView ID;

    @BindView(R.id.posts_post_title_label)
    TextView title;

    @BindView(R.id.posts_body_label)
    TextView body;

    @BindView(R.id.posts_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.posts_result_layout)
    RelativeLayout resultLayout;

    @BindView(R.id.posts_clear_button)
    ImageButton clearButton;

    private InputMethodManager imm;


    private PostsPresenter postsPresenter;

    public PostsViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        imm = (InputMethodManager) itemView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        postsPresenter = new PostsPresenter(this);

        itemView.clearFocus();
    }

    @Override
    public void showLoading() {
       // imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
        imm.hideSoftInputFromWindow(itemView.getWindowToken(), 0);
        resultLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        expandButton.setVisibility(View.INVISIBLE);
        expandButton.setImageResource(R.drawable.expand_button);
    }


    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
        expandButton.setVisibility(View.VISIBLE);
    }


    @Override
    public void showBondaryError() {
        errorLabel.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDownloadError() {
        Toasty.error(itemView.getContext(), itemView.getContext().getString(R.string.error_download),
               Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void initResult(Post post) {
        userID.setText(itemView.getContext().getString(R.string.user_id) + post.getUserId().toString());
        ID.setText(itemView.getContext().getString(R.string.id) + post.getId().toString());
        title.setText(itemView.getContext().getString(R.string.title) + post.getTitle());
        body.setText(itemView.getContext().getString(R.string.body) + post.getBody());
    }

    @Override
    public void showResult() {
        expandButton.setImageResource(R.drawable.expand_arrow);
        resultLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideResult() {
        expandButton.setImageResource(R.drawable.expand_button);
        resultLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideError() {
        errorLabel.setVisibility(View.INVISIBLE);
    }

    @Override
    public void clearTextView() {
        editText.setText("");
        editText.requestFocus();
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }


    @OnClick(R.id.posts_action_button)
    public void confirmButtonClick() {
        Integer n;
        if(!editText.getText().toString().equals("")){
            n = Integer.parseInt(editText.getText().toString());
        }
        else { n = null; }
        postsPresenter.postLoad(n);
    }

    @OnClick(R.id.posts_clear_button)
    public void clarButtonClick() {
        postsPresenter.clearText();
    }

    @OnClick(R.id.posts_expand_button)
    public void expandButtonClick() {
        if (resultLayout.getVisibility() == View.VISIBLE) {
            postsPresenter.hideResult();
        }
        else postsPresenter.showResult();
    }
}
