package com.zzt8888.materialdesign.main_ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzt8888.base.BaseFragment;
import com.zzt8888.beans.TypeDataBean;
import com.zzt8888.listeners.OnRcvScrollListener;
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
        present.loadData(20, 1);
    }

    private void initUi() {

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recycleView.setLayoutManager(layoutManager);

        adapter = new WelfareAdapter(getActivity());
        recycleView.setAdapter(adapter);


        freshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                freshLayout.setRefreshing(false);
            }
        });

        recycleView.addOnScrollListener(new OnRcvScrollListener() {
            @Override
            public void onBottom() {
                present.loadData(20,adapter.getItemCount()/20);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        present.detach();
    }

    @Override
    public void loading() {
        if (freshLayout != null) freshLayout.setRefreshing(true);
    }

    @Override
    public void showResult(TypeDataBean bean) {
        dismissLoading();
        if (adapter != null)
        adapter.addAll(bean.getResults());
    }

    @Override
    public void dismissLoading() {
        if (freshLayout != null)
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
