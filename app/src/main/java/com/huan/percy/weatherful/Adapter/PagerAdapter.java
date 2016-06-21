package com.huan.percy.weatherful.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.huan.percy.weatherful.Fragments.FutureFragment;
import com.huan.percy.weatherful.Fragments.InfoFragment;
import com.huan.percy.weatherful.Fragments.NowFragment;

/**
 * Created by Percy on 2016/6/21.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    int mNumOfTabs;
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new NowFragment();
            case 1:
                return new InfoFragment();
            case 2:
                return new FutureFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
