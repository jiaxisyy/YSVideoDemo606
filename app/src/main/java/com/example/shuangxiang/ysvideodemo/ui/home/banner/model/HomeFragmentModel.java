package com.example.shuangxiang.ysvideodemo.ui.home.banner.model;

import android.content.Context;

import com.example.shuangxiang.ysvideodemo.api.ApiManager;
import com.example.shuangxiang.ysvideodemo.ui.home.banner.presenter.HomeFragmentPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shuang.xiang on 2017/4/21.
 */

public class HomeFragmentModel implements IHomeFragmentModel {
    private HomeFragmentPresenter mPresenter;
    private Context mContext;
    private List<String> mListBanners = new ArrayList<>();

    public HomeFragmentModel(HomeFragmentPresenter presenter, Context context) {
        mPresenter = presenter;
        mContext = context;
    }

    @Override
    public void getBanner() {
        Observable<String[]> observable = ApiManager.getInstance().getBannersUrl();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<String[]>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String[] banners) {
                        mListBanners = Arrays.asList(banners);
                        mPresenter.requestSucceed(mListBanners);

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
