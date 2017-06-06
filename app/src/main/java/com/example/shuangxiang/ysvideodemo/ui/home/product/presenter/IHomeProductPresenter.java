package com.example.shuangxiang.ysvideodemo.ui.home.product.presenter;

import com.example.shuangxiang.ysvideodemo.ui.home.product.bean.ProductInfo;

/**
 * Created by shuang.xiang on 2017/4/24.
 */

public interface IHomeProductPresenter {

    void load();

    void requestSucceed(ProductInfo productInfo);

    void requestFailed();
}
