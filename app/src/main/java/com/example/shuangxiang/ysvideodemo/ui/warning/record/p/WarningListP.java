package com.example.shuangxiang.ysvideodemo.ui.warning.record.p;

import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.bean.WarningInfo;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.m.IWarningListM;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.m.WarningListM;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.v.IWarningListV;

import java.text.ParseException;
import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/27.
 */

public class WarningListP implements IWarningListP {
    private IWarningListV mView;
    private IWarningListM mModel;

    public WarningListP(IWarningListV view) {
        mView = view;
        mModel = new WarningListM(this);
    }

    @Override
    public void getResouce() {
        try {
            mModel.getResouce(mView.getPageNum(), mView.getPageSize(), mView.getFromDate(), mView
                    .getToDate(), mView.getDeviceId(Constants.Define.WARNINGRECORDTYPE_ONE));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getResouceSucceed(WarningInfo data) {
        List<WarningInfo.ListBean> list = data.getList();
        mView.setData(list);
    }

    @Override
    public void getRefreshResouce() {

    }

    @Override
    public void getRefreshResouceSucceed(WarningInfo data) {

    }

    @Override
    public void getLoadMoreResouce() {

    }

    @Override
    public void getLoadMoreResouceSucceed(WarningInfo data) {

    }
}
