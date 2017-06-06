package com.example.shuangxiang.ysvideodemo.ui.warning;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.decoration.MyDecoration;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.adapter.WarningListRVAllAdapter;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.bean.WarningInfo;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.p.WarningListP;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.search.WarningListSearchActivity;
import com.example.shuangxiang.ysvideodemo.ui.warning.record.v.IWarningListV;

import java.text.ParseException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shuang.xiang on 2017/4/27.
 */

public class WarningRecordFragment extends BaseFragment implements IWarningListV {
    @BindView(R.id.iv_warning_search)
    ImageView mIvWarningSearch;
    @BindView(R.id.rv_warning_list)
    RecyclerView mRecyclerView;
    private WarningListRVAllAdapter mAdapter;
    private WarningListP mPresenter;
    private ProgressDialog mProgressDialog;


    public WarningRecordFragment() {
    }

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile WarningRecordFragment instance;

    //定义一个共有的静态方法，返回该类型实例
    public static WarningRecordFragment getInstance() {
        if (instance == null) {
            synchronized (WarningRecordFragment.class) {
                if (instance == null) {
                    instance = new WarningRecordFragment();
                }
            }
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_warning_record;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initData() {
        mPresenter = new WarningListP(this);
        mPresenter.getResouce();
//        mProgressDialog = new ProgressDialog(getActivity());
//        mProgressDialog.show();

    }

    @Override
    protected boolean isCache() {
        return true;
    }

    @Override
    public void setData(List<WarningInfo.ListBean> data) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new WarningListRVAllAdapter(data, getActivity());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
//        mProgressDialog.dismiss();

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


    @OnClick(R.id.iv_warning_search)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), WarningListSearchActivity.class));

    }
}
