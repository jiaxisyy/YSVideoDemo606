package com.example.shuangxiang.ysvideodemo.login.model;

import android.content.Context;
import android.util.Log;

import com.example.shuangxiang.ysvideodemo.api.ApiManager;
import com.example.shuangxiang.ysvideodemo.login.bean.LoginErrorInfo;
import com.example.shuangxiang.ysvideodemo.login.bean.LoginInfo;
import com.example.shuangxiang.ysvideodemo.login.presenter.LoginPresenter;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit.User;

/**
 * Created by shuang.xiang on 2017/4/7.
 */

public class LoginModel implements ILoginModel {
    private LoginPresenter mLoginPresenter;
    private Context mContext;


    public LoginModel(LoginPresenter loginPresenter, Context context) {
        this.mLoginPresenter = loginPresenter;
        this.mContext = context;
    }

    @Override
    public void getLoginInfo(User user) {
        Observable<String> observable = ApiManager.getInstance().getLoginRequest(user.getUsername
                (), user
                .getPassword());
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Gson gson = new Gson();
                        if (s.contains("\"error\"")) {
                            //失败
                            LoginErrorInfo loginErrorInfo = gson.fromJson(s, LoginErrorInfo.class);
                            mLoginPresenter.loginFailed(loginErrorInfo.getError());
                        } else {
                            LoginInfo loginInfo = gson.fromJson(s, LoginInfo.class);
                            mLoginPresenter.loginSucceed(loginInfo);
                        }
                        Log.d("TEST", s.toString());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("ERROR", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }

                });


    }
}
