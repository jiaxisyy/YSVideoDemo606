package com.example.shuangxiang.ysvideodemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;

/**
 * Created by shuang.xiang on 2017/4/19.
 */

public class TestActivity extends Activity {

    private WebView wvtest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
//        initialize();
//        init();
        warningTest();


    }

    private void warningTest() {
        int temp = 0;
        int y = 0;//读取的值
        while (true) {

            if (y == 1 && temp == 0) {
                //记录一次预警产生
                temp = 1;
            } else if (y == 0 && temp == 1) {
                //记录一次报警消失
                temp =0;
            }
        }


    }

    private void init() {

        wvtest.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvtest.loadUrl(Constants.Define.BASE_URL);

    }


}
