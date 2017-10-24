package com.company.superandyeyev.atlantteamtask.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.company.superandyeyev.atlantteamtask.R;
import com.company.superandyeyev.atlantteamtask.ui.fragment.CardListFragment;
import com.company.superandyeyev.atlantteamtask.ui.fragment.MyContactsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        fragmentManager.beginTransaction().add(R.id.frame_layout, new CardListFragment(),
                getString(R.string.fragment_tag_one)).commit();


        bottomNavigationView.setOnNavigationItemSelectedListener
                (item -> {
                    switch (item.getItemId()) {
                        case R.id.action_map:
                            onClickMainScreen();
                            break;
                        case R.id.action_dial:
                            onClickContactsScreen();
                            break;
                    }
                    return true;
                });

    }

    //скрываем MyContactsFragment, показываем CardListFragment
    private void onClickMainScreen(){
        if(fragmentManager.findFragmentByTag(getString(R.string.fragment_tag_two)) != null){
            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(getString(R.string.fragment_tag_two))).commit();
        }
        if(fragmentManager.findFragmentByTag(getString(R.string.fragment_tag_one)) != null) {
            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(getString(R.string.fragment_tag_one))).commit();
        }
    }

    //скрываем CardListFragment, показываем MyContactsFragment
    private void onClickContactsScreen() {
        if(fragmentManager.findFragmentByTag(getString(R.string.fragment_tag_one)) != null) {
            fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(getString(R.string.fragment_tag_one))).commit();
        }
        if(fragmentManager.findFragmentByTag(getString(R.string.fragment_tag_two)) != null) {
            fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(getString(R.string.fragment_tag_two))).commit();
        } else {
            fragmentManager.beginTransaction().add(R.id.frame_layout, new MyContactsFragment(),getString(R.string.fragment_tag_two)).commit();
        }
    }
}
