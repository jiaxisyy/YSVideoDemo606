package com.example.shuangxiang.ysvideodemo.ui.data.analyze.m;

import android.util.Log;

import com.example.shuangxiang.ysvideodemo.api.ApiManager;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.bean.TableIdInfo;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.p.DataAnalyzeP;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shuang.xiang on 2017/5/22.
 */

public class DataAnalyzeM implements IDataAnalyzeM {
    private DataAnalyzeP mDataAnalyzeP;

    public DataAnalyzeM(DataAnalyzeP dataAnalyzeP) {
        mDataAnalyzeP = dataAnalyzeP;
    }

    @Override
    public void getTableId(String url) {
        Observable<TableIdInfo[]> observable = ApiManager.getInstance().getAnalyzeTableId(url);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TableIdInfo[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TableIdInfo[] tableIdInfo) {
                        mDataAnalyzeP.getTableIdSucceed(tableIdInfo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ERROR", "DataAnalyzeM->" + e.getMessage().toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public void getStatistics(String url) {
        ApiManager.getInstance().getAnalyzeStatistics(url).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                mDataAnalyzeP.getStatisticsSucceed(s);
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
}
