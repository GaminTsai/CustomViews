package com.zzt8888.materialdesign.main_ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;

import java.util.List;

public class SimpleFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public SimpleFragmentAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
        super(fragmentManager);
        this.fragments = fragments;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        Bundle bundle = fragments.get(position).getArguments();
        if (bundle != null) {
            String title = bundle.getString("TITLE");
            if (!TextUtils.isEmpty(title))
                return title;
        }
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
