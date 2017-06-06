package com.example.shuangxiang.ysvideodemo.ui.mydevice;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baidu.mapapi.map.TextureMapView;
import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CustomToast;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.p.MyDeviceListP;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.v.IMyDeviceListV;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.map.p.MyDeviceMapP;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.map.v.IMyDeviceMapV;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shuang.xiang on 2017/4/20.
 */

public class MyDeviceMapFragment extends BaseFragment implements IMyDeviceMapV, IMyDeviceListV {

    private static final int ACCESS_COARSE_LOCATION_REQUEST_CODE = 2;
    @BindView(R.id.mapView_mydevice)
    TextureMapView mMapView;
    @BindView(R.id.ll_mydevice_on)
    LinearLayout mLlOn;
    @BindView(R.id.ll_mydevice_off)
    LinearLayout mLlOff;
    @BindView(R.id.ll_mydevice_all)
    LinearLayout mLlAll;
    private MyDeviceMapP mPresenter;
    private ProgressDialog mProgressDialog;
    private boolean mIsFirstInto = true;
    private List<MyDeviceInfo.ListBean> mMList;
    private MyDeviceListP mMyDeviceListP;


    public MyDeviceMapFragment() {
    }

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile MyDeviceMapFragment instance;

    //定义一个共有的静态方法，返回该类型实例
    public static MyDeviceMapFragment getInstance() {
        if (instance == null) {
            synchronized (MyDeviceMapFragment.class) {
                if (instance == null) {
                    instance = new MyDeviceMapFragment();
                }
            }
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mydevice_pager_map;
    }

    @Override
    protected void init() {
        mPresenter = new MyDeviceMapP(this, getActivity(), mMapView);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.show();


    }


    @Override
    protected void initData() {
        mMyDeviceListP = new MyDeviceListP(this);
        mMyDeviceListP.getAllDevice();
    }

    @Override
    protected boolean isCache() {
        return true;
    }

    @Override
    public void showDeviceOn() {

    }

    @Override
    public void showDeviceOff() {

    }

    @Override
    public void showDeviceAll() {


    }

    @Override
    public void showToast(String toast) {
        CustomToast.showToast(getActivity(), toast, Toast.LENGTH_SHORT);
    }


    @OnClick({R.id.ll_mydevice_on, R.id.ll_mydevice_off, R.id.ll_mydevice_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_mydevice_on:
                mPresenter.clickOn();
                break;
            case R.id.ll_mydevice_off:
                mPresenter.clickOff();
                break;
            case R.id.ll_mydevice_all:
                mPresenter.clickAll();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        Log.d("TEST", "MyDeviceMapFragment->onDestroy");
        if (mMapView != null) {
            mMapView.onDestroy();
        }
        mMyDeviceListP.dispose();
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
    public void setData(List<String> names, List<String> status, List<String> ids, List<String> dataTemplateIds, List<MyDeviceInfo.ListBean> list) {

        if (mIsFirstInto) {
            mPresenter.initBaiDuMap(list);
            mIsFirstInto = false;
        }
        mProgressDialog.dismiss();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getPagerNum() {
        return Constants.Define.DEFAULTPAGENUM;
    }

    @Override
    public int getPagerSize() {
        return Constants.Define.DEFAULTPAGESIZE;
    }

    @Override
    public void refresh() {

    }

    @Override
    public void upload() {

    }
}
