package com.example.shuangxiang.ysvideodemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.bean.EZAccessToken;

/**
 * Created by shuang.xiang on 2017/2/14.
 */

public class EzvizBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "EzvizBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (TextUtils.equals(intent.getAction(), "com.videogo.action.OAUTH_SUCCESS_ACTION")) {

            EZAccessToken ezAccessToken = EZOpenSDK.getInstance().getEZAccessToken();
            Log.d(TAG, "success");
            String accessToken = ezAccessToken.getAccessToken();

            String s="hello";


        }
    }
}
