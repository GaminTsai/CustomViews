package com.zzt8888.materialdesign.main_ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SimpleFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public SimpleFragmentAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
        super(fragmentManager);
        this.fragments = fragments;
    }



    @Override
    public CharSequence getPageTitle(int position) {

        return "TAB" + position;
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }


}
