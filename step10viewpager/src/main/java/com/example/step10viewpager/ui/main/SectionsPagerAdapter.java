package com.example.step10viewpager.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    String[] roomNames={"첫번째방","두번째방"};

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {


        return PlaceholderFragment.newInstance("주인 없음");
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return roomNames[position];
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}