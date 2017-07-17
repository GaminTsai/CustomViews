package com.zzt8888.materialdesign.main_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzt8888.base.BaseFragment;
import com.zzt8888.beans.TypeDataBean;
import com.zzt8888.materialdesign.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends BaseFragment implements IWelfare {

    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    @BindView(R.id.fresh_layout)
    SwipeRefreshLayout freshLayout;

    @Inject
    TypeDataPresent present;

    private Unbinder unbinder;
    private WelfareAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        present.attachView(this);
        initUi();
        present.loadData(10,1);
    }

    private void initUi() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recycleView.setLayoutManager(layoutManager);

        adapter = new WelfareAdapter(getContext());
        recycleView.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        present.detach();
    }

    @Override
    public void loading() {
        freshLayout.setRefreshing(true);
    }

    @Override
    public void showResult(TypeDataBean bean) {
        adapter.addAll(bean.getResults());
    }

    @Override
    public void dismissLoading() {
        freshLayout.setRefreshing(false);
    }

    public static MainFragment newInstance(String title) {
        MainFragment mainFragment = new MainFragment();

        Bundle bundle = new Bundle();
        bundle.putString("TITLE", title);
        mainFragment.setArguments(bundle);

        return mainFragment;
    }
}
