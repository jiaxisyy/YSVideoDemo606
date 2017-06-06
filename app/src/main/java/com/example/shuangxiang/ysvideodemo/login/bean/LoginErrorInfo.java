package com.example.shuangxiang.ysvideodemo.login.bean;

/**
 * Created by shuang.xiang on 2017/4/10.
 */

public class LoginErrorInfo {


    /**
     * count : 9
     * error : 密码错误，您还有9次机会
     */

    private int count;
    private String error;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
