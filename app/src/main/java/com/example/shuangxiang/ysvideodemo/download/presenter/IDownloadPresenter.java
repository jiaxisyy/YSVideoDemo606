package com.example.shuangxiang.ysvideodemo.download.presenter;

import com.example.shuangxiang.ysvideodemo.download.bean.AppMessage;

/**
 * Created by shuang.xiang on 2017/4/11.
 */

public interface IDownloadPresenter {
    void startDownload();

    void stopDownload();

    void getAppMessageSucceed(AppMessage message);

    void getAppMessageFailed();

    void checkVersion();




}
