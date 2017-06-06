package com.example.shuangxiang.ysvideodemo.ui.home.banner.presenter;

import android.content.Context;

import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.ui.home.banner.model.HomeFragmentModel;
import com.example.shuangxiang.ysvideodemo.ui.home.banner.model.IHomeFragmentModel;
import com.example.shuangxiang.ysvideodemo.ui.home.banner.view.IHomeFragmentView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/21.
 */

public class HomeFragmentPresenter implements IHomeFragmentPresenter {
    private IHomeFragmentModel mModel;
    private IHomeFragmentView mView;
    private Context mContext;


    public HomeFragmentPresenter(IHomeFragmentView view, Context context) {
        mModel = new HomeFragmentModel(this, context);
        mView = view;
        mContext = context;

    }

    @Override
    public void loadBanner() {
        getBannerResource();
    }

    @Override
    public void getBannerResource() {
        mModel.getBanner();
    }

    @Override
    public void requestSucceed(List<String> list) {

        List<String> strings = new ArrayList<>();
        for (String url : list) {
            strings.add(Constants.Define.BASE_URL + url);
        }
        mView.setBannersListUrl(strings);
    }
}
