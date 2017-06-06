package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.p;

import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.m.IMyDeviceListM;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.m.MyDeviceListM;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.v.IMyDeviceListV;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public class MyDeviceListP implements IMyDeviceListP {
    private IMyDeviceListV mView;
    private IMyDeviceListM mModel;

    public MyDeviceListP() {
    }

    public MyDeviceListP(IMyDeviceListV view) {
        mView = view;
        mModel = new MyDeviceListM(this);
    }

    @Override
    public void getAllDevice() {
        //查询条件
        mModel.getAllResouce(null, mView.getName(), mView.getPagerNum(), mView.getPagerSize());
    }

    @Override
    public void getAllDeviceSucceed(List<MyDeviceInfo.ListBean> list) {
        List<String> names = new ArrayList<>();
        List<String> status = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        List<String> dataTemplateIds = new ArrayList<>();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            names.add(list.get(i).getName());
            status.add(list.get(i).getOnlineStatus());
            ids.add(list.get(i).getId());
            dataTemplateIds.add(list.get(i).getDataTemplateId());
        }
        mView.setData(names, status, ids, dataTemplateIds, list);
    }


    @Override
    public void dispose() {
        CompositeDisposable compositeDisposable = mModel.onDestroy();
        if(compositeDisposable!=null){
            compositeDisposable.dispose();
        }
    }
}
