package com.example.shuangxiang.ysvideodemo.ui.mydevice.map.p;

import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/5/4.
 */

public interface IMydeviceMapP {

    void clickOn();

    void clickOff();

    void clickAll();

    void addMaker(double latitude, double lontitude, int makerType);

    void initBaiDuMap(List<MyDeviceInfo.ListBean> list);


}
