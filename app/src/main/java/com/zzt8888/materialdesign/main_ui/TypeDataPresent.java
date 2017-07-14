package com.zzt8888.materialdesign.main_ui;

import com.zzt8888.APIService;
import com.zzt8888.base.Presenter;
import com.zzt8888.tools.RxSchedulersHelper;

import javax.inject.Inject;

public class TypeDataPresent implements Presenter<IWelfare> {

    private APIService apiService;
    private IWelfare iWelfare;

    @Inject
    public TypeDataPresent(APIService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void attachView(IWelfare iView) {
        this.iWelfare = iView;
    }

    @Override
    public void detach() {
        iWelfare.dismissLoading();
        this.iWelfare = null;
    }

    public void loadData(int count,int index){
        apiService.getDataByCategory("",count,index)
        .compose(RxSchedulersHelper.io_main())
        .subscribe(typeDataBean -> {iWelfare.showResult(typeDataBean);},
                e->{},()->{});
    }
}
