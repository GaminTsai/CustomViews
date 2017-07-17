package com.zzt8888.base;

import android.support.v4.app.Fragment;

import com.zzt8888.components.DaggerFragmentComponent;
import com.zzt8888.components.FragmentComponent;
import com.zzt8888.dagger.ApplicationComponent;
import com.zzt8888.dagger.FragmentModule;

public class BaseFragment extends Fragment {

    protected FragmentComponent getFragmentComponent() {
        ApplicationComponent applicationComponent = ((MaterialApplication) getActivity().getApplication()).getComponent();
        return DaggerFragmentComponent.builder()
                .applicationComponent(applicationComponent)
                .fragmentModule(getFragmentModule())
                .build();
    }


    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }
}
