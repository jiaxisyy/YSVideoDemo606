package com.example.shuangxiang.ysvideodemo.download.view;

import android.widget.PopupWindow;

/**
 * Created by shuang.xiang on 2017/4/11.
 */

public interface IDownloadView {
    void showProgress();

    void hideProgress();

    void showUpdateMessage(String message);

    void showNewestVersion();

    void hintNewestVersion();

    void setVersionName(String versionName);

    void showUpdatePop(PopupWindow popupWindow);

    void hintUpdatePop(PopupWindow popupWindow);
}
