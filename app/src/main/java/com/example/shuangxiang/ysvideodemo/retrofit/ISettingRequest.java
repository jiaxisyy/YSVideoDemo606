package com.example.shuangxiang.ysvideodemo.retrofit;

import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.bean.ParameterInfo;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Url;

/**
 * Created by shuang.xiang on 2017/5/18.
 */

public interface ISettingRequest {
    /**
     * 获取参数设置的标题
     *
     * @param url
     * @return
     */
    @GET
    Observable<ParameterInfo[]> getParameterTitle(@Url String url);

    /**
     *
     * 获取参数设置的值
     * @param url
     * @return
     */
    @GET
    Observable<String> getParameterValue(@Url String url);


    /**
     *
     * 设置参数
     * @param url
     * @return
     */
    @PUT
    Observable<String> setParameterValue(@Url String url, @Body RequestBody body);


}
