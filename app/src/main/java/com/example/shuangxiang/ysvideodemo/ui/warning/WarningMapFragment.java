package com.example.shuangxiang.ysvideodemo.ui.warning;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.TextureMapView;
import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.warning.map.p.WarningMapP;
import com.example.shuangxiang.ysvideodemo.ui.warning.map.v.IWarningMapV;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.bean.WarningInfo;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.p.WarningListP;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.v.IWarningListV;

import java.text.ParseException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shuang.xiang on 2017/4/27.
 */

public class WarningMapFragment extends BaseFragment implements IWarningListV, IWarningMapV {
    @BindView(R.id.mapView_warning)
    TextureMapView mMapView;
    @BindView(R.id.ll_warning_map_red)
    LinearLayout mLlMapRed;
    @BindView(R.id.ll_warning_map_orange)
    LinearLayout mLlMapOrange;
    @BindView(R.id.ll_warning_map_yellow)
    LinearLayout mLlMapYellow;
    @BindView(R.id.ll_warning_map_green)
    LinearLayout mLlMapGreen;
    @BindView(R.id.ll_warning_map_all)
    LinearLayout mLlMapAll;
    @BindView(R.id.tv_warning_map_numRed)
    TextView mTvNumRed;
    @BindView(R.id.tv_warning_map_numYellow)
    TextView mTvNumYellow;
    @BindView(R.id.tv_warning_map_numOrange)
    TextView mTvNumOrange;
    @BindView(R.id.tv_warning_map_numGreen)
    TextView mTvNumGreen;
    private WarningListP mWarningListP;
    private WarningMapP mWarningMapP;



    public WarningMapFragment() {
    }

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile WarningMapFragment instance;

    //定义一个共有的静态方法，返回该类型实例
    public static WarningMapFragment getInstance() {
        if (instance == null) {
            synchronized (WarningMapFragment.class) {
                if (instance == null) {
                    instance = new WarningMapFragment();
                }
            }
        }
        return instance;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_warning_map;
    }

    @Override
    protected void init() {
        mWarningListP = new WarningListP(this);
        mWarningListP.getResouce();
        mMapView.showZoomControls(false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean isCache() {
        return true;
    }

    @OnClick({R.id.ll_warning_map_red, R.id.ll_warning_map_orange, R.id.ll_warning_map_yellow, R.id.ll_warning_map_green, R.id.ll_warning_map_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_warning_map_red:
                mWarningMapP.clickRed();
                break;
            case R.id.ll_warning_map_orange:
                mWarningMapP.clickOrange();
                break;
            case R.id.ll_warning_map_yellow:
                mWarningMapP.clickYellow();
                break;
            case R.id.ll_warning_map_green:
                mWarningMapP.clickGreen();
                break;
            case R.id.ll_warning_map_all:
                mWarningMapP.clickAll();
                break;
        }
    }

    @Override
    public void setData(List<WarningInfo.ListBean> data) {
        mWarningMapP = new WarningMapP(this, mMapView, getActivity());
        mWarningMapP.init();
        mWarningMapP.setData(data);

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
        return null;
    }

    @Override
    public String getToDate() {
        return null;
    }

    @Override
    public String getDeviceId(int type) throws ParseException {
        if (type == Constants.Define.WARNINGRECORDTYPE_ONE) {//查看某一台
            String deviceId = CacheUtils.getString(getActivity(), Constants.Define.MYDEVICE_TO_SECONDHOME_ID);
            if (deviceId != null && !deviceId.equals("")) {
                return deviceId;
            }
        }
        return "";
    }

    @Override
    public void setRedNum(int num) {
        mTvNumRed.setText(String.valueOf(num));

    }

    @Override
    public void setOrangeNum(int num) {
        mTvNumOrange.setText(String.valueOf(num));
    }

    @Override
    public void setYellowNum(int num) {
        mTvNumYellow.setText(String.valueOf(num));
    }

    @Override
    public void setGreenNum(int num) {
        mTvNumGreen.setText(String.valueOf(num));
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        Log.d("TEST", "MyDeviceMapFragment->onDestroy");
        if (mMapView != null) {
            mMapView.onDestroy();
        }
    }
}
