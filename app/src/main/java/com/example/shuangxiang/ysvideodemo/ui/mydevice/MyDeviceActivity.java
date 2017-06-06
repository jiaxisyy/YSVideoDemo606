package com.example.shuangxiang.ysvideodemo.ui.mydevice;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.PermissionUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.manager.ActivityManager;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.p.MyDeviceListP;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.v.IMyDeviceListV;
import com.example.shuangxiang.ysvideodemo.ui.warning.WarningActivity;
import com.zhy.autolayout.utils.ScreenUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shuang.xiang on 2017/4/20.
 */

public class MyDeviceActivity extends BaseActivity implements IMyDeviceListV,ActivityCompat.OnRequestPermissionsResultCallback {
    @BindView(R.id.iv_mydevice_warning)
    ImageView mIvWarning;
    private MyDeviceListP mPresenter;
    @BindView(R.id.tb_mydevice)
    Toolbar mTbMydevice;

    @BindView(R.id.tbl_mydevice)
    TabLayout mTabLayout;
//    @BindView(R.id.vp_mydevice)
//    ViewPager mViewPager;
    @BindView(R.id.tv_mydevice_allDevice)
    TextView mTvAllDevice;
    @BindView(R.id.tv_mydevice_deviceOn)
    TextView mTvMyDeviceOn;
    @BindView(R.id.tv_mydevice_deviceOff)
    TextView mTvMyDeviceOff;

    private List<String> tb_titles;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mydevice);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initSomething() {
        ActivityManager.getInstance().addActivity(new WeakReference<Activity>(this));
        mTbMydevice.setTitle("");
        setSupportActionBar(mTbMydevice);
        mTbMydevice.setNavigationIcon(R.drawable.icon_mydevice_back);
        setImmerseLayout(mTbMydevice);//状态栏颜色设置
        tb_titles = new ArrayList<>();
        //添加标题
        tb_titles.add(Constants.Define.DEVICELIST.toString());
        tb_titles.add(Constants.Define.DEVICEMAP.toString());

        mTabLayout.setLayoutMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < tb_titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tb_titles.get(i)));
        }
        initView();
        initData();

        //改变为不滑动
//        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void initData() {
        mPresenter = new MyDeviceListP(this);
        mPresenter.getAllDevice();
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_ACCESS_FINE_LOCATION,
                mPermissionGrant);

    }


    private void initView() {
//        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
//        myViewPagerAdapter.addFragment(MyDeviceListFragment.getInstance(), Constants.Define.DEVICELIST);
//        myViewPagerAdapter.addFragment(MyDeviceMapFragment.getInstance(), Constants.Define.DEVICEMAP);
//        mViewPager.setAdapter(myViewPagerAdapter);
        //设置默认界面列表
        Utils.replace(getSupportFragmentManager(), R.id.fl_myDevice,
                MyDeviceListFragment.class);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position==0){
                    Utils.replace(getSupportFragmentManager(), R.id.fl_myDevice,
                            MyDeviceListFragment.class);
                }else if(position==1){
                    Utils.replace(getSupportFragmentManager(), R.id.fl_myDevice,
                            MyDeviceMapFragment.class);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults,
                mPermissionGrant);

    }
    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_ACCESS_FINE_LOCATION:

                    break;
                case PermissionUtils.CODE_ACCESS_COARSE_LOCATION:

                    break;
            }
        }
    };

    /**
     * 状态栏颜色设置
     *
     * @param view 与顶部view相同
     */
    protected void setImmerseLayout(View view) {
        //先将状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //获取状态栏的高度
            int statusBarHeight = ScreenUtils.getStatusBarHeight(this);
            //将顶部空间的top padding设置为和状态栏一样的高度，以此达到预期的效果
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.d("TEST", "MyDeviceActivity->onOptionsItemSelected1");
                finish();
                Log.d("TEST", "MyDeviceActivity->onOptionsItemSelected2");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setData(List<String> names, List<String> status,List<String> ids,List<String> dataTemplateIds, List<MyDeviceInfo.ListBean> list) {
        int size = names.size();
        if(size>0){
            mTvAllDevice.setText(String.valueOf(size));
            int countOn = 0;
            for (int i = 0; i < size; i++) {
                if (status.get(i).equals("ONLINE")) {
                    countOn++;
                }
            }
            mTvMyDeviceOn.setText(String.valueOf(countOn));
            mTvMyDeviceOff.setText(String.valueOf(size - countOn));
        }
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
        return Constants.Define.MAXPAGESIZE;
    }

    @Override
    public void refresh() {

    }

    @Override
    public void upload() {

    }


    @OnClick(R.id.iv_mydevice_warning)
    public void onViewClicked() {
        //查询所有
        CacheUtils.putString(this,Constants.Define.MYDEVICE_TO_SECONDHOME_ID,"");
        startActivity(new Intent(this, WarningActivity.class));
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return false;

    }
}
