package com.example.shuangxiang.ysvideodemo.feedback.m;

import android.util.Log;

import com.example.shuangxiang.ysvideodemo.api.ApiManager;
import com.example.shuangxiang.ysvideodemo.feedback.bean.FeedbackInfo;
import com.example.shuangxiang.ysvideodemo.feedback.bean.FilePath;
import com.example.shuangxiang.ysvideodemo.feedback.p.FeedbackP;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public class FeedbackM implements IFeedbackM {
    private FeedbackP presenter;

    public FeedbackM(FeedbackP presenter) {
        this.presenter = presenter;
    }

    @Override
    public void uploadFile(File file) {
        Observable<FilePath> observable = ApiManager.getInstance().uploadFile(file);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<FilePath>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FilePath filePath) {
                        presenter.uploadFileSucceed(filePath.getPath());
                        Log.d("TEST", "uploadFile->" + filePath.getPath().toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void submit(FeedbackInfo info) {
        Observable<String> observable = ApiManager.getInstance().submit(info);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                presenter.submitSucceed(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }


}
