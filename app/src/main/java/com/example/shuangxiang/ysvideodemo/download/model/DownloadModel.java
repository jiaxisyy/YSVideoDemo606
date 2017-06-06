package com.example.shuangxiang.ysvideodemo.download.model;

import android.util.Log;

import com.example.shuangxiang.ysvideodemo.api.ApiManager;
import com.example.shuangxiang.ysvideodemo.download.bean.AppMessage;
import com.example.shuangxiang.ysvideodemo.download.presenter.DownloadPresernter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shuang.xiang on 2017/4/11.
 */

public class DownloadModel implements IDownloadModel {
    private DownloadPresernter mPresernter;

    public DownloadModel(DownloadPresernter presernter) {
        mPresernter = presernter;
    }

    @Override
    public void getAppMessage() {
        Observable<AppMessage> observable = ApiManager.getInstance().getAppMessage();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppMessage>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(AppMessage appMessage) {

                        mPresernter.getAppMessageSucceed(appMessage);
                        Log.d("TEST", appMessage.toString());

                    }

                    @Override
                    public void onError(Throwable e) {
                        mPresernter.getAppMessageFailed();
                        Log.e("ERROR", "DownloadModel->"+e.getMessage().toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void requestDownload() {
        //



    }
}
