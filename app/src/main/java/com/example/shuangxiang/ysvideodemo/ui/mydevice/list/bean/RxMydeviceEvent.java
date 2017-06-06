package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/5/15.
 */

public class RxMydeviceEvent {
    private List<MyDeviceInfo.ListBean> mList;

    public RxMydeviceEvent(List<MyDeviceInfo.ListBean> list) {
        mList = list;
    }

    public List<MyDeviceInfo.ListBean> getList() {
        return mList;
    }

    public void setList(List<MyDeviceInfo.ListBean> list) {
        mList = list;
    }
}
