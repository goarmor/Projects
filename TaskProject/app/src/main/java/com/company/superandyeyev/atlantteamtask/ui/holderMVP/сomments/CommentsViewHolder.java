package com.company.superandyeyev.atlantteamtask.ui.holderMVP.сomments;

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
import com.company.superandyeyev.atlantteamtask.rest.Model.Comment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * Created by DIMON on 10.10.2017.
 */

public class CommentsViewHolder extends RecyclerView.ViewHolder implements CommentsView {

    @BindView(R.id.comments_edit_text)
    EditText editText;

    @BindView(R.id.comments_action_button)
    Button confirmButton;

    @BindView(R.id.comments_postID_label)
    TextView postIDResult;

    @BindView(R.id.comments_ID_label)
    TextView idResult;

    @BindView(R.id.comments_name_title_label)
    TextView nameResult;

    @BindView(R.id.comments_email_title_label)
    TextView emailResult;

    @BindView(R.id.comments_body_label)
    TextView bodyResult;

    @BindView(R.id.comments_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.comments_result_layout)
    RelativeLayout resultLayout;

    @BindView(R.id.comments_clear_button)
    ImageButton clearButton;

    @BindView(R.id.comments_expand_button)
    ImageButton expandButton;

    @BindView(R.id.comments_error_label)
    TextView errorLabel;

    private InputMethodManager imm;

    private CommentsPresenter commentsPresenter;

    public CommentsViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        commentsPresenter = new CommentsPresenter(this);

        imm = (InputMethodManager) itemView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public void showLoading() {
        imm.hideSoftInputFromWindow(itemView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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

    @Override
    public void showDownloadError() {
        Toasty.error(itemView.getContext(), itemView.getContext().getString(R.string.error_download),
                Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void showBondaryError() {
        errorLabel.setVisibility(View.VISIBLE);
    }

    @Override
    public void initResult(Comment comment) {
        postIDResult.setText(itemView.getContext().getString(R.string.post_id) + comment.getPostId());
        idResult.setText(itemView.getContext().getString(R.string.id) + comment.getId());
        nameResult.setText(itemView.getContext().getString(R.string.name) + comment.getName());
        emailResult.setText(itemView.getContext().getString(R.string.email) + comment.getEmail());
        bodyResult.setText(itemView.getContext().getString(R.string.body) + comment.getBody());
    }

    @OnClick(R.id.comments_action_button)
    public void confirmButtonClick() {
        Integer n;
        if(!editText.getText().toString().equals("")){
            n = Integer.parseInt(editText.getText().toString());
        }
        else { n = null; }
        commentsPresenter.commentLoad(n);
    }

    @OnClick(R.id.comments_clear_button)
    public void clarButtonClick() {
        commentsPresenter.clearText();
    }

    @OnClick(R.id.comments_expand_button)
    public void expandButtonClick() {
        if (resultLayout.getVisibility() == View.VISIBLE) {
            commentsPresenter.hideResult();
        }
        else commentsPresenter.showResult();
    }
}
