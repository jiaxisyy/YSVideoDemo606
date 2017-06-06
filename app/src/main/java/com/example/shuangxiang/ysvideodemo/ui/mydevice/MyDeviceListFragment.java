package com.example.shuangxiang.ysvideodemo.ui.mydevice;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.CustomToast;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.SecondHomeActivity;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.adapter.MydeviceListRVAdapter;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.decoration.MyDecoration;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.p.MyDeviceListP;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.v.IMyDeviceListV;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.search.MyDeviceListSearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shuang.xiang on 2017/4/20.
 */

public class MyDeviceListFragment extends BaseFragment implements IMyDeviceListV {

    @BindView(R.id.rb_mydevice_on)
    RadioButton mRbOn;
    @BindView(R.id.rb_mydevice_off)
    RadioButton mRbOff;
    @BindView(R.id.rb_mydevice_all)
    RadioButton mRbAll;
    private MyDeviceListP mPresenter;
    @BindView(R.id.rg_mydevice_list)
    RadioGroup mRgMydeviceList;
    @BindView(R.id.iv_mydevice_search)
    ImageView mIvMydeviceSearch;
    @BindView(R.id.rv_mydevice_list)
    RecyclerView mRecyclerView;
    private MydeviceListRVAdapter mAdapter;
    private boolean mIsFirstInto = true;


    public MyDeviceListFragment() {
    }

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile MyDeviceListFragment instance;

    //定义一个共有的静态方法，返回该类型实例
    public static MyDeviceListFragment getInstance() {
        if (instance == null) {
            synchronized (MyDeviceListFragment.class) {
                if (instance == null) {
                    instance = new MyDeviceListFragment();
                }
            }
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {


        return R.layout.mydevice_pager_list;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {
        mPresenter = new MyDeviceListP(this);

        if (mIsFirstInto) {
            mPresenter.getAllDevice();
            mIsFirstInto = false;
        }

    }

    @Override
    protected boolean isCache() {
        return true;
    }


    @OnClick(R.id.iv_mydevice_search)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), MyDeviceListSearchActivity.class));
    }

    @Override
    public void setData(final List<String> names, final List<String> status, final List<String>
            ids, final List<String> dataTemplateIds, List<MyDeviceInfo.ListBean> list) {
        if (names == null || status == null || ids == null || dataTemplateIds == null) {
            CustomToast.showToast(getActivity(), "数据显示错误", Toast.LENGTH_SHORT);
        }
        //初始化在线
        final List<String> namesOn = new ArrayList<>();
        final List<String> statusOn = new ArrayList<>();
        final List<String> idsOn = new ArrayList<>();
        final List<String> dataTemplateIdsOn = new ArrayList<>();
        int size = names.size();
        for (int i = 0; i < size; i++) {
            if (status.get(i).equals("ONLINE")) {
                namesOn.add(names.get(i));
                statusOn.add("ONLINE");
                idsOn.add(ids.get(i));
                dataTemplateIdsOn.add(dataTemplateIds.get(i));
            }
        }
        //初始化离线
        final List<String> namesOff = new ArrayList<>();
        final List<String> statusOff = new ArrayList<>();
        final List<String> idsOff = new ArrayList<>();
        final List<String> dataTemplateIdsOff = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (status.get(i).equals("OFFLINE")) {
                namesOff.add(names.get(i));
                statusOff.add("OFFLINE");
                idsOff.add(ids.get(i));
                dataTemplateIdsOff.add(dataTemplateIds.get(i));
            }
        }
        mRgMydeviceList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rb_mydevice_on:
//                        if (namesOn == null || namesOn.size() == 0) {
//                            CustomToast.showToast(getActivity(), "数据显示错误", Toast.LENGTH_SHORT);
//                        }
                        if (namesOn != null) {
                            MydeviceListRVAdapter adapterOn = new MydeviceListRVAdapter(namesOn,
                                    statusOn, getActivity());
                            mRecyclerView.setAdapter(adapterOn);
                            adapterOn.setOnItemClickListener(new MydeviceListRVAdapter.MyItemClickListener() {
                                @Override
                                public void onItemClick(View view, int postion) {
                                    Log.d("TEST", "position=" + postion);
                                    Log.d("TEST", "position=" + postion + "name=" + namesOn.get(postion));
                                    CacheUtils.putString(getActivity(), Constants.Define
                                            .MYDEVICE_TO_SECONDHOME_ID, idsOn.get(postion));
                                    CacheUtils.putString(getActivity(), Constants.Define
                                            .MYDEVICE_TO_SECONDHOME_TBTITLE, namesOn.get(postion));
                                    CacheUtils.putString(getActivity(), Constants.Define
                                            .MYDEVICE_TO_SECONDHOME_DATATEMPLATEID, dataTemplateIdsOn.get(postion));
                                    startActivity(new Intent(getActivity(), SecondHomeActivity.class));
                                }
                            });
                            break;
                        }

                    case R.id.rb_mydevice_off:
//                        if (namesOff == null || namesOff.size() == 0) {
//                            CustomToast.showToast(getActivity(), "数据显示错误", Toast.LENGTH_SHORT);
//                        }
                        if (namesOff != null) {
                            MydeviceListRVAdapter adapterOff = new MydeviceListRVAdapter(namesOff,
                                    statusOff, getActivity());
                            mRecyclerView.setAdapter(adapterOff);
//                        Log.d("TEST", "name=" + namesOff.get(0));
                            adapterOff.setOnItemClickListener(new MydeviceListRVAdapter.MyItemClickListener() {
                                @Override
                                public void onItemClick(View view, int postion) {
                                    Log.d("TEST", "position=" + postion + "name=" + namesOff.get(postion));
                                    CacheUtils.putString(getActivity(), Constants.Define
                                            .MYDEVICE_TO_SECONDHOME_ID, idsOff.get(postion));
                                    CacheUtils.putString(getActivity(), Constants.Define
                                            .MYDEVICE_TO_SECONDHOME_DATATEMPLATEID, dataTemplateIdsOff
                                            .get(postion));
                                    CacheUtils.putString(getActivity(), Constants.Define
                                            .MYDEVICE_TO_SECONDHOME_TBTITLE, namesOff.get(postion));
                                    startActivity(new Intent(getActivity(), SecondHomeActivity.class));
                                }
                            });
                            break;

                        }
//                        mAdapter.setData(namesOff, statusOff);

                    case R.id.rb_mydevice_all:
//                        if (names == null || names.size() == 0) {
//                            CustomToast.showToast(getActivity(), "数据显示错误", Toast.LENGTH_SHORT);
//                        }
                        if (names != null) {
                            MydeviceListRVAdapter adapterAll = new MydeviceListRVAdapter(names,
                                    status, getActivity());
                            mRecyclerView.setAdapter(adapterAll);
                            adapterAll.setOnItemClickListener(new MydeviceListRVAdapter.MyItemClickListener() {
                                @Override
                                public void onItemClick(View view, int postion) {
                                    Log.d("TEST", "position=" + postion + "name=" + names.get(postion));
                                    CacheUtils.putString(getActivity(), Constants.Define
                                            .MYDEVICE_TO_SECONDHOME_ID, ids.get(postion));
                                    CacheUtils.putString(getActivity(), Constants.Define
                                            .MYDEVICE_TO_SECONDHOME_DATATEMPLATEID, dataTemplateIds.get(postion));
                                    CacheUtils.putString(getActivity(), Constants.Define
                                            .MYDEVICE_TO_SECONDHOME_TBTITLE, names.get(postion));
                                    startActivity(new Intent(getActivity(), SecondHomeActivity.class));
                                }
                            });
                            break;
                        }
//                        mAdapter.setData(names, status);

                }

            }
        });
        //默认显示在线
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);
        if (namesOn == null) {
            CustomToast.showToast(getActivity(), "数据显示错误", Toast.LENGTH_SHORT);
        }
        mAdapter = new MydeviceListRVAdapter(namesOn, statusOn, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        mAdapter.setOnItemClickListener(new MydeviceListRVAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Log.d("TEST", "position=" + postion);
                Log.d("TEST", "position=" + postion + "name=" + namesOn.get(postion));
                CacheUtils.putString(getActivity(), Constants.Define
                        .MYDEVICE_TO_SECONDHOME_ID, idsOn.get(postion));
                CacheUtils.putString(getActivity(), Constants.Define
                        .MYDEVICE_TO_SECONDHOME_DATATEMPLATEID, dataTemplateIdsOn.get(postion));
                CacheUtils.putString(getActivity(), Constants.Define
                        .MYDEVICE_TO_SECONDHOME_TBTITLE, namesOn.get(postion));
                startActivity(new Intent(getActivity(), SecondHomeActivity.class));
            }
        });


    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        if(mAdapter!=null){
//            mAdapter.notifyDataSetChanged();
//        }
//    }

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.dispose();
    }
}
