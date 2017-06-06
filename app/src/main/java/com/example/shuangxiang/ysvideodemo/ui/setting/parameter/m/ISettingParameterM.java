package com.example.shuangxiang.ysvideodemo.ui.setting.parameter.m;

import com.example.shuangxiang.ysvideodemo.ui.IBaseModel;

/**
 * Created by shuang.xiang on 2017/5/17.
 */

public interface ISettingParameterM  extends IBaseModel{
    void getParameterTitle(String url);
    void getParameterValue(String url);
    void setParameterValue(String url,String json);
}
