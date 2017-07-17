package com.zzt8888.dagger;

import android.support.v4.app.Fragment;


import com.zzt8888.scopes.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    private final Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    Fragment getFragment() {
        return this.fragment;
    }
}
