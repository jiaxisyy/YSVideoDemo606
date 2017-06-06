package com.example.shuangxiang.ysvideodemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shuang.xiang on 2017/4/19.
 */

public abstract class BaseFragment extends Fragment {

    private boolean mIsCompleted = false;
    private Unbinder mUnbinder;
    private LayoutInflater mInflater;
    private View mView;

    /**
     * 获取布局文件ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract void init();

    protected abstract void initData();

    protected abstract boolean isCache();

    private View mLayoutView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (isCache()) {
            if (mLayoutView == null) {
                mLayoutView = inflate(inflater);
                onInflateView(mLayoutView);
                mIsCompleted = true;
            } else {
                ViewGroup parent = (ViewGroup) mLayoutView.getParent();
                if (parent != null)
                    parent.removeView(mLayoutView);
            }
            mInflater = inflater;
            mUnbinder = ButterKnife.bind(this, mLayoutView);
        } else {
            mView = inflater.inflate(getLayoutId(), container, false);
            mUnbinder = ButterKnife.bind(this, mView);
            initData();
            init();
            return mView;
        }
        initData();
        init();
        return mLayoutView;
    }

    protected View inflate(LayoutInflater inflater) {
        return inflater.inflate(getLayoutId(), null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    /**
     * 只运行一次
     */
    public void onInflateView(View contentView) {

    }
}
