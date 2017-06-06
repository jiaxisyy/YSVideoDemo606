package com.example.shuangxiang.ysvideodemo.ui.warning.map.p;

import com.example.shuangxiang.ysvideodemo.ui.warning.record.bean.WarningInfo;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/5/10.
 */

public interface IWarningMapP {
    void clickRed();

    void setRedNum(int num);

    void clickOrange();

    void setOrangeNum(int num);

    void clickYellow();

    void setYellowNum(int num);

    void clickGreen();

    void setGreenNum(int num);

    void clickAll();

    void init();

    void addMaker(double latitude, double lontitude, int makerType);
    void setData(List<WarningInfo.ListBean> data);


}
