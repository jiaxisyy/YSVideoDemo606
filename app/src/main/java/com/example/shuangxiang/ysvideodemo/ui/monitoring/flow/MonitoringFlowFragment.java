package com.example.shuangxiang.ysvideodemo.ui.monitoring.flow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.shuangxiang.ysvideodemo.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shuang.xiang on 2017/5/20.
 */

public class MonitoringFlowFragment extends Fragment {

    private Unbinder mUnbinder;

    private int getLayoutId() {
        return R.layout.fragment_monitoring_flow;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
