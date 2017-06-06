package com.example.shuangxiang.ysvideodemo.feedback.p;

import com.example.shuangxiang.ysvideodemo.feedback.bean.FeedbackInfo;

import java.io.File;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public interface IFeedbackP {
    void uploadFile(File file);

    void uploadFileSucceed(String imagePath);

    void submit(FeedbackInfo info);

    void submitSucceed(String s);
}
