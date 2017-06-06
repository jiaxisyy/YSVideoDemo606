package com.example.shuangxiang.ysvideodemo.rxbus;

/**
 * Created by shuang.xiang on 2017/5/3.
 */

public class RxEvent {
    private int code;
    private Object object;
    public RxEvent(int code, Object object){
        this.code=code;
        this.object=object;
    }
    public RxEvent(){}

    public int getCode() {
        return code;
    }

    public Object getObject() {
        return object;
    }
}
