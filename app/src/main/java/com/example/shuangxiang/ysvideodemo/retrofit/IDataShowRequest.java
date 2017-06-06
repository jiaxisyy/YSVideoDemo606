package com.example.shuangxiang.ysvideodemo.retrofit;

import com.example.shuangxiang.ysvideodemo.ui.data.show.bean.DataShowBottomTitle;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by shuang.xiang on 2017/5/16.
 */

public interface IDataShowRequest {

    @GET
    Observable<List<DataShowBottomTitle>> getDataShowBottomTitle(@Url String url);

}
