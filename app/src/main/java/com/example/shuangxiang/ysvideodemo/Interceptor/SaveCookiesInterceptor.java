package com.example.shuangxiang.ysvideodemo.Interceptor;

import android.content.Context;

import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by shuang.xiang on 2017/4/11.
 */

public class SaveCookiesInterceptor implements Interceptor {
    private Context mContext;

    public SaveCookiesInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            String header = originalResponse.header("Set-Cookie");
            CacheUtils.putString(mContext, Constants.Define.COOKIE, header);
        }

        return originalResponse;
    }
}