package com.company.superandyeyev.vkgroupreader.ui.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.company.superandyeyev.vkgroupreader.R;

/**
 * Created by DIMON on 31.10.2017.
 */

public class MyPreferencesFragment extends PreferenceFragment {

    public MyPreferencesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

    }
}