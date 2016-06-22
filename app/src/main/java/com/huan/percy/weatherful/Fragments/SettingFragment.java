package com.huan.percy.weatherful.Fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.huan.percy.weatherful.R;

/**
 * Created by Percy on 2016/6/22.
 */
public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.setting_layout);

    }
}
