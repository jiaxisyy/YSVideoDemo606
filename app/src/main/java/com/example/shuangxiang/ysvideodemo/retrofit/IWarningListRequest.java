package com.example.shuangxiang.ysvideodemo.retrofit;

import com.example.shuangxiang.ysvideodemo.ui.warning.record.bean.WarningInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public interface IWarningListRequest {
    @GET("deviceAlarms")
    Observable<WarningInfo> getRecord(@Query("pageNum") int pageNum, @Query("pageSize") int
            pageSize, @Query("fromDate") String fromDate, @Query("toDate") String toDate,@Query
            ("deviceId") String deviceId);
}
