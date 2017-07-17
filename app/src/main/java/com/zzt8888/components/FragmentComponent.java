package com.zzt8888.components;

import android.support.v4.app.Fragment;

import com.zzt8888.dagger.ApplicationComponent;
import com.zzt8888.dagger.FragmentModule;
import com.zzt8888.materialdesign.main_ui.MainFragment;
import com.zzt8888.scopes.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies = ApplicationComponent.class, modules = {FragmentModule.class})
public interface FragmentComponent {

    Fragment getFragment();

    void inject(MainFragment fragment);
}
