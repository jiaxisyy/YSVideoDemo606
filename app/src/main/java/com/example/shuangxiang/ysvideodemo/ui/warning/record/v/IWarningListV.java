package com.example.shuangxiang.ysvideodemo.ui.warning.record.v;

import com.example.shuangxiang.ysvideodemo.ui.warning.record.bean.WarningInfo;

import java.text.ParseException;
import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/27.
 */

public interface IWarningListV {

    void setData(List<WarningInfo.ListBean> data);

    void refresh(List<WarningInfo.ListBean> data);

    void loadMore(List<WarningInfo.ListBean> data);

    int getPageNum();

    int getPageSize();

    String getFromDate() throws ParseException;

    String getToDate() throws ParseException;
    String getDeviceId(int type) throws ParseException;


}
