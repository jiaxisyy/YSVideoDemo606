package com.example.shuangxiang.ysvideodemo.ui.setting.parameter.p;

import com.example.shuangxiang.ysvideodemo.ui.IBasePresenter;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.bean.ParameterInfo;

/**
 * Created by shuang.xiang on 2017/5/17.
 */

public interface ISettingParameterP extends IBasePresenter{
    void getTitleSucceed(ParameterInfo[] list);

    void getTitle(String type);

    void getValueSucceed(String s);

    void setValue(String url, String value,String password);

    void setValueSucceed(String s);

    void setValueFailed(String s);
    void onError(String s);

}
