package com.zzt8888.tools;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 处理Rx线程
 */
public class RxSchedulersHelper {

    public static <T> Observable.Transformer<T, T> io_main() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
//            return new Observable.Transformer<T, T>() {
//            @Override
//            public Observable<T> call(Observable<T> tObservable) {
//                return tObservable
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread());
//            }

    }


}