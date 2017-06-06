package com.example.shuangxiang.ysvideodemo.retrofit;

import com.example.shuangxiang.ysvideodemo.ui.data.analyze.bean.TableIdInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by shuang.xiang on 2017/5/22.
 */

public interface IDataAnalyzeRequest {
    /**
     *
     * 获取表id
     * @param url
     * @return
     */
    @GET
    Observable<TableIdInfo[]> getTableId(@Url String url);


    /**
     *
     * 运行统计
     * @param url
     * @return
     */
    @GET
    Observable<String> getStatistics(@Url String url);











}
