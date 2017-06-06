package com.example.shuangxiang.ysvideodemo.ui.setting.parameter.m;

import android.util.Log;

import com.example.shuangxiang.ysvideodemo.api.ApiManager;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.bean.ParameterInfo;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.p.SettingParameterP;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shuang.xiang on 2017/5/18.
 */

public class SettingParameterM implements ISettingParameterM {
    private SettingParameterP mSettingParameterP;
    private CompositeDisposable cd = new CompositeDisposable();

    public SettingParameterM(SettingParameterP settingParameterP) {
        mSettingParameterP = settingParameterP;
    }

    @Override
    public void getParameterTitle(String url) {

        ApiManager.getInstance().getParameterTitle(url).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread()).subscribe(new Observer<ParameterInfo[]>() {
            @Override
            public void onSubscribe(Disposable d) {
                cd.add(d);
            }

            @Override
            public void onNext(ParameterInfo[] parameterInfos) {
                mSettingParameterP.getTitleSucceed(parameterInfos);
            }

            @Override
            public void onError(Throwable e) {


//                mSettingParameterP.setValueFailed(Constants.Define.SERVERQUERSTERROR);
                Log.e("ERROR", "SettingParameterM->" + e.getMessage().toString());
            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void getParameterValue(String url) {
        ApiManager.getInstance().getParameterValue(url).repeatWhen(new Function<Observable<Object>,
                ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
                return objectObservable.delay(Constants.Define.REFRESH_INTERVAL, TimeUnit.SECONDS);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()).subscribe
                (new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        cd.add(d);

                    }

                    @Override
                    public void onNext(String s) {
                        mSettingParameterP.getValueSucceed(s);
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
    public void setParameterValue(String url, String json) {
        ApiManager.getInstance().setParameterValue(url, json).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onNext(String s) {
                        if (s.contains("success")) {
                            mSettingParameterP.setValueSucceed(s);
                        } else {
                            mSettingParameterP.setValueFailed(s);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
//                        mSettingParameterP.onError(Constants.Define.SERVERQUERSTERROR);
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
