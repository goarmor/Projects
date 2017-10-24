package com.company.superandyeyev.atlantteamtask.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.superandyeyev.atlantteamtask.R;
import com.company.superandyeyev.atlantteamtask.common.Adapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DIMON on 11.10.2017.
 */

public class CardListFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view_fragment1, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recyclerView.setAdapter(new Adapter());

    }
}
