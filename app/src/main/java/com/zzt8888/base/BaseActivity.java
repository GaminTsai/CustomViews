package com.zzt8888.base;

import android.support.v7.app.AppCompatActivity;

import com.zzt8888.dagger.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {


//    protected ActivityComponent getActivityComponent() {
//        ApplicationComponent applicationComponent = ((MaterialApplication) getApplication()).getComponent();
//        return DaggerActivityComponent.builder()
//                .applicationComponent(applicationComponent)
//                .activityModule(getActivityModule())
//                .build();
//    }


    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

}
