package com.example.shuangxiang.ysvideodemo.feedback.v;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public interface IFeedBackV {
    void showDialog();

    void showProgressBar();

    void hideDialog();

    void hideProgressBar();

    String getFeedbackPhone();

    String getFeedbackMessage();

    void showToast(String s);

}
