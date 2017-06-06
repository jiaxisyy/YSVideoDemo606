package com.example.shuangxiang.ysvideodemo.ui.setting.parameter.bean;

/**
 * Created by shuang.xiang on 2017/5/25.
 */

public class ParameterRequestInfo {


    public ParameterRequestInfo(String value, String password) {
        this.value = value;
        this.password = password;
    }

    /**
     * value : 2016
     * password : chin2016
     */

    private String value;
    private String password;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
