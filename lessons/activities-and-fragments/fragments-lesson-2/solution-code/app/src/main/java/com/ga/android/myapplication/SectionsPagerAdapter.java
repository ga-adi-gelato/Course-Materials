package com.ga.android.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by alanjcaceres on 7/19/16.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private AListFragment.OnListItemClickListener mListItemClickListener;

    public SectionsPagerAdapter(FragmentManager fm,
                                AListFragment.OnListItemClickListener listener) {
        super(fm);
        mListItemClickListener = listener;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a AListFragment (defined as a static inner class below).
        return AListFragment.newInstance(position, mListItemClickListener);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            default:
            case 0:
                return "Planets";
            case 1:
                return "Groceries";
            case 2:
                return "To Do List";
        }

    }
}
