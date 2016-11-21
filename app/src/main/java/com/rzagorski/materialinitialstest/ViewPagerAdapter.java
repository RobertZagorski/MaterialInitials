package com.rzagorski.materialinitialstest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rzagorski.materialinitialstest.base.InitialsFragment;

/**
 * Created by Robert Zagórski on 2016-11-21.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    Fragment[] mFragmentList;

    public ViewPagerAdapter(FragmentManager fm, Fragment[] fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ((InitialsFragment) mFragmentList[position]).getTitle();
    }

    @Override
    public int getCount() {
        return mFragmentList.length;
    }
}
