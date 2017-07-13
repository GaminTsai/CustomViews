package com.zzt8888.base;

import android.app.Application;

import com.zzt8888.dagger.ApplicationComponent;
import com.zzt8888.dagger.ApplicationModule;
import com.zzt8888.dagger.DaggerApplicationComponent;

public class MaterialApplication extends Application {
    private ApplicationComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mComponent.inject(this);
    }
}
