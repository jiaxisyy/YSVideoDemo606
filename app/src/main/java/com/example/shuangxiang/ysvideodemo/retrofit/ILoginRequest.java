package com.example.shuangxiang.ysvideodemo.retrofit;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by shuang.xiang on 2017/4/7.
 */

public interface ILoginRequest {

    /**
     *
     * 用户登录,表单形式提交
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Observable<String> loginRequest(@Field("username") String username, @Field("password") String
            password);

}
