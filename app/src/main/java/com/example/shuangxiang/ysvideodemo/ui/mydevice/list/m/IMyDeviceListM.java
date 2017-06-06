package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.m;

import com.example.shuangxiang.ysvideodemo.ui.IBaseModel;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public interface IMyDeviceListM  extends IBaseModel{
    void getAllResouce(String orgId, String name, int pageNum, int pageSize);
}
