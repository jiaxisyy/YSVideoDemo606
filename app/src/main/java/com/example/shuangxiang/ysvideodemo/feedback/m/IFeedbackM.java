package com.example.shuangxiang.ysvideodemo.feedback.m;

import com.example.shuangxiang.ysvideodemo.feedback.bean.FeedbackInfo;

import java.io.File;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public interface IFeedbackM {
    void uploadFile(File file);

    void submit(FeedbackInfo info);
}
