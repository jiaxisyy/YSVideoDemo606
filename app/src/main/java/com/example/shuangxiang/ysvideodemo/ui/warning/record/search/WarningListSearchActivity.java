package com.example.shuangxiang.ysvideodemo.ui.warning.record.search;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.CustomToast;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.decoration.MyDecoration;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.adapter.WarningListRVAdapter;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.bean.WarningInfo;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.p.IWarningListP;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.p.WarningListP;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.search.v.IWarningListSearchV;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.v.IWarningListV;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shuang.xiang on 2017/4/27.
 */

public class WarningListSearchActivity extends BaseActivity implements IWarningListSearchV, IWarningListV {
    @BindView(R.id.tv_warning_listSearch_startTime)
    TextView mTvStartTime;
    @BindView(R.id.tv_warning_listSearch_endTime)
    TextView mTvEndTime;
    @BindView(R.id.tv_warning_listSearch_cancel)
    TextView mTvCancel;
    @BindView(R.id.rv_warning_list_search)
    RecyclerView mRv;
    //    @BindView(R.id.animation_view)
//    LottieAnimationView mAnimationView;
    private Calendar mCalendar;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMin;
    private DatePickerDialog mDatePickerDialog;

    private int START_OR_END_FLAG;

    private IWarningListP mWarningListP;
    private WarningListRVAdapter mAdapter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_warning_list_search);
    }

    @Override
    protected void initSomething() {
        mProgressDialog = new ProgressDialog(WarningListSearchActivity.this);
        // 获取日历的一个对象
        mCalendar = Calendar.getInstance();
        // 获取年月日时分秒的信息
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mHour = mCalendar.get(Calendar.HOUR);
        mMin = mCalendar.get(Calendar.MINUTE);
        mWarningListP = new WarningListP(WarningListSearchActivity.this);
        mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                StringBuffer mDate;
                mDate = new StringBuffer().append(year).append("-").append
                        (month + 1).append("-").append(day);

                if (START_OR_END_FLAG == 1) {//开始时间
                    mTvStartTime.setText(mDate);

                } else if (START_OR_END_FLAG == 0) {
                    //结束时间
                    mTvEndTime.setText(mDate);
                }
                if (mTvStartTime.getText().toString().equals("") || mTvEndTime.getText().toString().equals
                        ("")) {
                    CustomToast.showToast(WarningListSearchActivity.this, "请输入起始时间", Toast.LENGTH_SHORT);
                } else {
                    if (mTvStartTime.getText().toString().equals(mTvEndTime.getText().toString())) {
                        CustomToast.showToast(WarningListSearchActivity.this, "请不要输入相同时间", Toast
                                .LENGTH_SHORT);
                    }
                    mWarningListP.getResouce();
                    Log.d("TEST", "哈哈");

                    mProgressDialog.show();
                }
            }
        }, mYear, mMonth, mDay);


    }

//    private void myAnimation() {
//        mAnimationView.setAnimation("TwitterHeart.json");
//        mAnimationView.loop(true);
//        mAnimationView.playAnimation();
//
//    }

    @OnClick({R.id.tv_warning_listSearch_startTime, R.id.tv_warning_listSearch_endTime, R.id.tv_warning_listSearch_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_warning_listSearch_startTime:
                START_OR_END_FLAG = 1;
                mDatePickerDialog.show();

                break;
            case R.id.tv_warning_listSearch_endTime:
                START_OR_END_FLAG = 0;
                mDatePickerDialog.show();
                break;
            case R.id.tv_warning_listSearch_cancel:
                finish();
                break;
        }
    }

    @Override
    public String getStartDate() {
        String startDate = null;
        try {
            startDate = Utils.dateToStamp(mTvStartTime.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    @Override
    public String getEndDate() {

        String endDate = null;
        try {
            endDate = Utils.dateToStamp(mTvEndTime.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }

    @Override
    public void setData(List<WarningInfo.ListBean> data) {

        if (data != null && data.size() > 0) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            mRv.setHasFixedSize(true);
            layoutManager.setAutoMeasureEnabled(true);
            mRv.setLayoutManager(layoutManager);
            mAdapter = new WarningListRVAdapter(data, this);
            mRv.setAdapter(mAdapter);
            mRv.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
        } else {
            CustomToast.showToast(this, Constants.Define.SEARCH_NOTHING, Toast.LENGTH_SHORT);
        }
        mProgressDialog.dismiss();
    }

    @Override
    public void refresh(List<WarningInfo.ListBean> data) {

    }

    @Override
    public void loadMore(List<WarningInfo.ListBean> data) {

    }

    @Override
    public int getPageNum() {
        return Constants.Define.DEFAULTPAGENUM;
    }

    @Override
    public int getPageSize() {
        return Constants.Define.DEFAULTPAGESIZE;
    }

    @Override
    public String getFromDate() {
        return getStartDate();
    }

    @Override
    public String getToDate() {
        return getEndDate();
    }

    @Override
    public String getDeviceId(int type) throws ParseException {
        if (type == Constants.Define.WARNINGRECORDTYPE_ONE) {//查看某一台
            String deviceId = CacheUtils.getString(this, Constants.Define
                    .MYDEVICE_TO_SECONDHOME_ID);
            if (deviceId != null && !deviceId.equals("")) {
                return deviceId;
            }
        }
        return "";


    }

}
