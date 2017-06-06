package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.m;

import android.util.Log;

import com.example.shuangxiang.ysvideodemo.api.ApiManager;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.p.MyDeviceListP;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public class MyDeviceListM implements IMyDeviceListM {
    private MyDeviceListP mPresenter;
    private CompositeDisposable cd = new CompositeDisposable();

    public MyDeviceListM(MyDeviceListP presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getAllResouce(String orgId, String name, int pageNum, int pageSize) {
        Observable<MyDeviceInfo> observable = ApiManager.getInstance().getAllDevices(orgId, name, pageNum,
                pageSize);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()).subscribe(new Observer<MyDeviceInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                cd.add(d);
            }

            @Override
            public void onNext(MyDeviceInfo myDeviceInfo) {
                List<MyDeviceInfo.ListBean> list = myDeviceInfo.getList();
                mPresenter.getAllDeviceSucceed(list);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("ERROR", e.getMessage().toString());
            }

            @Override
            public void onComplete() {

            }
        });


    }

    @Override
    public CompositeDisposable onDestroy() {
        if (cd != null && cd.size() > 0 && !cd.isDisposed()) {
            return cd;
        }
        return null;
    }
}
