package com.example.shuangxiang.ysvideodemo.ui.data.analyze.p;

import android.content.Context;
import android.util.Log;

import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.TimeUtil;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.bean.TableIdInfo;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.m.DataAnalyzeM;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.m.IDataAnalyzeM;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.v.IDataAnalyzeV;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuang.xiang on 2017/5/22.
 */

public class DataAnalyzeP implements IDataAnalyzeP {
    private IDataAnalyzeM mIDataAnalyzeM;
    private IDataAnalyzeV mIDataAnalyzeV;
    private String mTableId;
    private Context mContext;
    private String mDefaultAddress;


    public DataAnalyzeP(IDataAnalyzeV IDataAnalyzeV, Context context) {
        mIDataAnalyzeM = new DataAnalyzeM(this);
        mIDataAnalyzeV = IDataAnalyzeV;
        mContext = context;

    }

    @Override
    public void getTableIdSucceed(TableIdInfo[] tableIdInfo) {
        int length = tableIdInfo.length;
        for (int i = 0; i < length; i++) {
            if (tableIdInfo[i].getName().equals("数据表")) {
                mTableId = tableIdInfo[i].getId();
                Log.d("TEST", "DaaAnalyzeP->id=" + mTableId);
            }
        }


    }

    @Override
    public void getTableId(String url) {
        mIDataAnalyzeM.getTableId(url);
    }

    @Override
    public void getStatistics(String timeType, String defaultAddress) {
        String deviceid = CacheUtils.getString(mContext, Constants.Define.MYDEVICE_TO_SECONDHOME_ID);
//        String datatemplateid = CacheUtils.getString(mContext, Constants.Define.MYDEVICE_TO_SECONDHOME_DATATEMPLATEID);

        mDefaultAddress = defaultAddress;

        Long startTime = TimeUtil.getStartTime();
        long weekStartTime = TimeUtil.getTimesWeekmorning();
        long timesMonthmorning = TimeUtil.getTimesMonthmorning();
        long currentQuarterStartTime = TimeUtil.getCurrentQuarterStartTime();
        long currentYearFirst = TimeUtil.getCurrentYearFirst();
        long now = TimeUtil.getNow();

        Log.d("TEST", "DaaAnalyzeP->startTime=" + startTime);
        Log.d("TEST", "DaaAnalyzeP->weekStartTime=" + weekStartTime);
        Log.d("TEST", "DaaAnalyzeP->timesMonthmorning=" + timesMonthmorning);
        Log.d("TEST", "DaaAnalyzeP->currentQuarterStartTime=" + currentQuarterStartTime);
        Log.d("TEST", "DaaAnalyzeP->currentYearFirst=" + currentYearFirst);
        Log.d("TEST", "DaaAnalyzeP->now=" + now);


        long fromData = 0;

        String newTimeType = "";
        switch (timeType) {
            case Constants.Define.ANALYZETIMETYPE_DAY:
                fromData = startTime;
                newTimeType = Constants.Define.ANALYZETIMETYPE_HOUR;
                break;
            case Constants.Define.ANALYZETIMETYPE_WEEK:
                fromData = weekStartTime;
                newTimeType = Constants.Define.ANALYZETIMETYPE_DAY;
                break;
            case Constants.Define.ANALYZETIMETYPE_MONTH:
                fromData = timesMonthmorning;
                newTimeType = Constants.Define.ANALYZETIMETYPE_DAY;
                break;
            case Constants.Define.ANALYZETIMETYPE_QUARTER:
                fromData = currentQuarterStartTime;
                newTimeType = Constants.Define.ANALYZETIMETYPE_MONTH;
                break;
            case Constants.Define.ANALYZETIMETYPE_YEAR:
                fromData = currentYearFirst;
                newTimeType = Constants.Define.ANALYZETIMETYPE_MONTH;
                break;

        }

        /**
         *
         * 统计接口
         */
        String statisticsUrl = Constants.Define.BASE_URL + "devices/" + deviceid + "/elementTables/"
                + mTableId + "/stats?statFields=" + defaultAddress
                + "&fromDate=" + fromData + "&toDate=" + now + "&statDateInterval=" + newTimeType;
        Log.d("TEST", "DaaAnalyzeP->statisticsUrl=" + statisticsUrl);
        mIDataAnalyzeM.getStatistics(statisticsUrl);

    }

    @Override
    public void getStatisticsSucceed(String s) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray(Constants.Define.JSONARRAYNAME);
            int length = jsonArray.length();
            List<String> values = new ArrayList<>();
            for (int i = 0; i < length; i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                if (!object.getString(mDefaultAddress).equals(Constants.Define.JSON_NAN)) {
                    values.add(object.getString(mDefaultAddress));
                }
            }

            mIDataAnalyzeV.setLineChart(values);


            for (String value : values) {
                Log.d("TEST", "DaaAnalyzeP->value=" + value);
            }
            Log.d("TEST", "DaaAnalyzeP->size=" + values.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
