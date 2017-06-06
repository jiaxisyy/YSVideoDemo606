package com.example.shuangxiang.ysvideodemo.ui.mydevice.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.adapter.MydeviceListRVAdapter;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.bean.MyDeviceInfo;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.decoration.MyDecoration;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.p.MyDeviceListP;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.list.v.IMyDeviceListV;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by shuang.xiang on 2017/4/26.
 */

public class MyDeviceListSearchActivity extends BaseActivity implements IMyDeviceListV {

    private MyDeviceListP mPresenter;
    @BindView(R.id.et_mydevice_listSearch_input)
    EditText mEtInput;
    @BindView(R.id.tv_mydevice_listSearch_cancel)
    TextView mTvCancel;
    @BindView(R.id.rv_mydevice_list_search)
    RecyclerView mRecyclerView;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_mydevice_search);
    }

    @Override
    protected void initSomething() {
        mEtInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {

                    //点击关闭键盘
                    InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    in.hideSoftInputFromWindow(textView.getApplicationWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);

                    //借用设备列表的mvp
                    mPresenter = new MyDeviceListP(MyDeviceListSearchActivity.this);
                    mPresenter.getAllDevice();

                }
                return false;
            }
        });

    }

    @OnClick(R.id.tv_mydevice_listSearch_cancel)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void setData(List<String> names, List<String> status,List<String> ids,List<String> dataTemplateIds, List<MyDeviceInfo.ListBean> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        layoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new MydeviceListRVAdapter(names, status, this));
        mRecyclerView.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));
    }

    @Override
    public String getName() {
        return mEtInput.getText().toString();
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
}
