package com.example.shuangxiang.ysvideodemo.ui.data.show.p;

import com.example.shuangxiang.ysvideodemo.ui.data.show.bean.DataShowBottomTitle;
import com.example.shuangxiang.ysvideodemo.ui.data.show.m.DataShowBottomTitleM;
import com.example.shuangxiang.ysvideodemo.ui.data.show.m.IDataShowBottomTitleM;
import com.example.shuangxiang.ysvideodemo.ui.data.show.v.IDataShowBottomTitleV;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/5/16.
 */

public class DataShowBottomTitleP implements IDataShowBottomTitleP {
    private IDataShowBottomTitleM mBottomTitleM;
    private IDataShowBottomTitleV mBottomTitleV;

    public DataShowBottomTitleP(IDataShowBottomTitleV bottomTitleV) {
        mBottomTitleM = new DataShowBottomTitleM(this);
        mBottomTitleV = bottomTitleV;
    }

    @Override
    public void getTitleSucceed(List<DataShowBottomTitle> list) {




    }
}
