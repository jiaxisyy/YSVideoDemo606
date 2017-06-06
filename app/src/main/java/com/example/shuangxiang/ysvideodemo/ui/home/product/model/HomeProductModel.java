package com.example.shuangxiang.ysvideodemo.ui.home.product.model;

import android.util.Log;

import com.example.shuangxiang.ysvideodemo.api.ApiManager;
import com.example.shuangxiang.ysvideodemo.ui.home.product.bean.ProductInfo;
import com.example.shuangxiang.ysvideodemo.ui.home.product.presenter.HomeProductPresenter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shuang.xiang on 2017/4/24.
 */

public class HomeProductModel implements IHomeProductModel {
    private HomeProductPresenter mPresenter;

    public HomeProductModel(HomeProductPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getProductResouce() {

        Observable<ProductInfo> products = ApiManager.getInstance().getProducts();
        products.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io()).subscribe(new Observer<ProductInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProductInfo productInfo) {
                mPresenter.requestSucceed(productInfo);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("ERROR", "getProductResouceFailed" + e.getMessage().toString());

            }

            @Override
            public void onComplete() {

            }
        });


    }
}
