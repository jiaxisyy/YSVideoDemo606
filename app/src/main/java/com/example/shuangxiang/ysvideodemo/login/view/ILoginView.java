package com.example.shuangxiang.ysvideodemo.login.view;

import retrofit.User;

/**
 * Created by shuang.xiang on 2017/4/6.
 */

public interface ILoginView {

    String getUserName();

    String getPassWord();

    void showLoading();

    void hideLoading();

    void clearUserName();

    void clearPassWord();

    void toMainActivity(User user);

    boolean isRememberPassWord();

    void showFailedError(String error);

    void showPassWord();

    void hidePassWord();


}
