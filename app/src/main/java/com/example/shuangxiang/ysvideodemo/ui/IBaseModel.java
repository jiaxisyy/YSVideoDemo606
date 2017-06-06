package com.example.shuangxiang.ysvideodemo.ui;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by shuang.xiang on 2017/5/27.
 */

public interface IBaseModel {
    CompositeDisposable onDestroy();
}
