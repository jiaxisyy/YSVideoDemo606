package com.example.shuangxiang.ysvideodemo.login.presenter;

import com.example.shuangxiang.ysvideodemo.login.bean.LoginInfo;

import retrofit.User;

/**
 * Created by shuang.xiang on 2017/4/7.
 */

public interface ILoginPresenter {
    void login(User user);

    /**
     *
     * 登陆成功
     * @param info 返回成功的信息对象
     */
    void loginSucceed(LoginInfo info);

    void loginFailed(String info);


}
