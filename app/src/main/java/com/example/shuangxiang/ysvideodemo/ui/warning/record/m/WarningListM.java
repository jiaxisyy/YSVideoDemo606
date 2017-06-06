package com.example.shuangxiang.ysvideodemo.ui.warning.record.m;

import android.util.Log;

import com.example.shuangxiang.ysvideodemo.api.ApiManager;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.bean.WarningInfo;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.p.WarningListP;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shuang.xiang on 2017/4/27.
 */

public class WarningListM implements IWarningListM {
    private WarningListP mPresenter;

    public WarningListM(WarningListP presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getResouce(int pageNum, int pageSize, String fromDate, String toDate,String deviceId) {
        Observable<WarningInfo> observable = ApiManager.getInstance().getRecord(pageNum,
                pageSize, fromDate, toDate,deviceId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()).subscribe(new Observer<WarningInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WarningInfo warningInfo) {
                mPresenter.getResouceSucceed(warningInfo);
            }

            @Override
            public void onError(Throwable e) {

                Log.e("ERROR", "WarningListM->onError->" + e.getMessage().toString());

            }

            @Override
            public void onComplete() {

            }
        });


    }
}
