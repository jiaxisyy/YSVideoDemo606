package com.example.shuangxiang.ysvideodemo.ui.data.show.m;

import com.example.shuangxiang.ysvideodemo.api.ApiManager;
import com.example.shuangxiang.ysvideodemo.ui.data.show.bean.DataShowBottomTitle;
import com.example.shuangxiang.ysvideodemo.ui.data.show.p.DataShowBottomTitleP;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shuang.xiang on 2017/5/16.
 */

public class DataShowBottomTitleM implements IDataShowBottomTitleM {
    private DataShowBottomTitleP mBottomTitleP;
    private CompositeDisposable cd = new CompositeDisposable();

    public DataShowBottomTitleM(DataShowBottomTitleP bottomTitleP) {
        mBottomTitleP = bottomTitleP;
    }

    @Override
    public void getBottomTitle(String url) {
        Observable<List<DataShowBottomTitle>> observable = ApiManager.getInstance().getTitle(url);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<DataShowBottomTitle>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onNext(List<DataShowBottomTitle> list) {
                        mBottomTitleP.getTitleSucceed(list);
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
