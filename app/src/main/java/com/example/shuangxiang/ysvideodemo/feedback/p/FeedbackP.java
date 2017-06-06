package com.example.shuangxiang.ysvideodemo.feedback.p;

import android.util.Log;

import com.example.shuangxiang.ysvideodemo.feedback.bean.FeedbackInfo;
import com.example.shuangxiang.ysvideodemo.feedback.m.FeedbackM;
import com.example.shuangxiang.ysvideodemo.feedback.m.IFeedbackM;
import com.example.shuangxiang.ysvideodemo.feedback.v.IFeedBackV;

import java.io.File;

/**
 * Created by shuang.xiang on 2017/4/25.
 */

public class FeedbackP implements IFeedbackP {
    private IFeedbackM mModel;
    private IFeedBackV mView;

    public FeedbackP(IFeedBackV view) {
        mModel = new FeedbackM(this);
        mView = view;
    }

    @Override
    public void uploadFile(File file) {
        mView.showProgressBar();
        mModel.uploadFile(file);
    }

    @Override
    public void uploadFileSucceed(String imagePath) {

        String feedbackMessage = mView.getFeedbackMessage();
        String feedbackPhone = mView.getFeedbackPhone();

        submit(new FeedbackInfo(feedbackMessage, feedbackPhone, imagePath));


    }

    @Override
    public void submit(FeedbackInfo info) {
        mModel.submit(info);
//        Log.d("TEST", "FeedbackInfo->" + info.toString());
    }

    @Override
    public void submitSucceed(String s) {
        if (s.contains("createDate")) {
            mView.hideProgressBar();
            mView.showDialog();
        }

        Log.d("TEST", "submitSucceed->" + s);

    }
}
