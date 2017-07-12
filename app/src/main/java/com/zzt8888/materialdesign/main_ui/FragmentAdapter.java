package com.zzt8888.materialdesign.main_ui;

import android.support.v4.view.PagerAdapter;
import android.view.View;

public class FragmentAdapter extends PagerAdapter {

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

}
