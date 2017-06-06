package com.example.shuangxiang.ysvideodemo.ui.home.product.presenter;

import android.content.Context;

import com.example.shuangxiang.ysvideodemo.ui.home.product.bean.ProductInfo;
import com.example.shuangxiang.ysvideodemo.ui.home.product.model.HomeProductModel;
import com.example.shuangxiang.ysvideodemo.ui.home.product.model.IHomeProductModel;
import com.example.shuangxiang.ysvideodemo.ui.home.product.view.IHomeProductView;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/24.
 */

public class HomeProductPresenter implements IHomeProductPresenter {
    private IHomeProductView mView;
    private IHomeProductModel mModel;
    private Context mContext;

    public HomeProductPresenter(IHomeProductView view, Context context) {
        mView = view;
        mModel = new HomeProductModel(this);
        mContext = context;
    }

    @Override
    public void load() {
        mModel.getProductResouce();

    }

    @Override
    public void requestSucceed(ProductInfo productInfo) {
        List<ProductInfo.ListBean> list = productInfo.getList();
        mView.setProductResouce(list);
    }

    @Override
    public void requestFailed() {

    }
}
