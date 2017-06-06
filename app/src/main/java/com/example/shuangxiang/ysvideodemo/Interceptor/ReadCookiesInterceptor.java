package com.example.shuangxiang.ysvideodemo.Interceptor;

import android.content.Context;
import android.util.Log;

import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shuang.xiang on 2017/4/11.
 */

public class ReadCookiesInterceptor implements Interceptor {
    private Context mContext;

    public ReadCookiesInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String cookie = CacheUtils.getString(mContext, Constants.Define.COOKIE);

        builder.addHeader("Cookie", cookie);
        Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp


        return chain.proceed(builder.build());
    }
}
