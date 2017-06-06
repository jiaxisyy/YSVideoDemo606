package com.example.shuangxiang.ysvideodemo.ui.setting.parameter.v;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/5/17.
 */

public interface ISettingParameterV {
    void setRvData(List<String> names, List<String> values,List<String> ids,List<String> units,
                   List<String> defaultAddress);
    void setToast(String s);
    void dissDialog();
}
