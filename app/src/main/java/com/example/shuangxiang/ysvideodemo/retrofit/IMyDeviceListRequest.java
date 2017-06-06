package com.example.shuangxiang.ysvideodemo.retrofit;

import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public interface IMyDeviceListRequest {
    @GET("devices")
    Observable<MyDeviceInfo> getAllDevices(@Query("orgId") String orgId, @Query("name") String name,
                                           @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

}
