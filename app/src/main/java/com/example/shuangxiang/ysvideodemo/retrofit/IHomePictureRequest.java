package com.example.shuangxiang.ysvideodemo.retrofit;

import com.example.shuangxiang.ysvideodemo.ui.home.product.bean.ProductInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by shuang.xiang on 2017/4/21.
 */

public interface IHomePictureRequest {
    @GET("kawaapp/banners")
    Observable<String[]> getBannersUrl();

    @GET("kawaapp/products")
    Observable<ProductInfo> getProducts();

}
