package com.example.shuangxiang.ysvideodemo.ui.warning.record.p;

import com.example.shuangxiang.ysvideodemo.ui.warning.record.bean.WarningInfo;

/**
 * Created by shuang.xiang on 2017/4/27.
 */

public interface IWarningListP {
    void getResouce();

    void getResouceSucceed(WarningInfo data);

    void getRefreshResouce();

    void getRefreshResouceSucceed(WarningInfo data);

    void getLoadMoreResouce();

    void getLoadMoreResouceSucceed(WarningInfo data);
}
