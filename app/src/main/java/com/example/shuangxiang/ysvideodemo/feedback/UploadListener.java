package com.example.shuangxiang.ysvideodemo.feedback;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public interface UploadListener {
    void onRequestProgress(long bytesWritten, long contentLength);
}
