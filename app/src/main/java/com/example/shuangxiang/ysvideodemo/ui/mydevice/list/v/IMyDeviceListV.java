package com.example.shuangxiang.ysvideodemo.ui.mydevice.list.v;

import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public interface IMyDeviceListV {

    void setData(List<String> names, List<String> status,List<String> ids,List<String>
            dataTemplateIds,List<MyDeviceInfo.ListBean> list);

    String getName();

    int getPagerNum();

    int getPagerSize();

    void refresh();

    void upload();

}
