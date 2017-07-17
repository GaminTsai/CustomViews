package com.zzt8888.components;

import android.app.Activity;


import com.zzt8888.dagger.ActivityModule;
import com.zzt8888.dagger.ApplicationComponent;
import com.zzt8888.materialdesign.main_ui.MainActivity;
import com.zzt8888.scopes.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);

}