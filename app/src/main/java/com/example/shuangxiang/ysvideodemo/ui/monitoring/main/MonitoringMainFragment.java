package com.example.shuangxiang.ysvideodemo.ui.monitoring.main;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.shuangxiang.ysvideodemo.R;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EZPlayer;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;

/**
 * Created by shuang.xiang on 2017/5/20.
 */

public class MonitoringMainFragment extends Fragment {
    private static final String TAG = "TEST";
//    @BindView(R.id.tv_monitoring_videoTitle)
//    TextView mTvTitle;
//    @BindView(R.id.iv_monitoring_videoNotice)
//    ImageView mIvNotice;
//    @BindView(R.id.tb_monitoring_video)
//    Toolbar mTb;
    private int mErrorCode = -1;
    private EZOpenSDK mInstance;
    private SurfaceHolder mRealPlaySh = null;
    private EZPlayer mMEZPlayer;
    private Unbinder mUnbinder;


    private int getLayoutId() {
        return R.layout.fragment_monitoring_main;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(getLayoutId(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {

    }

    protected void setImmerseLayout(View view) {
        //先将状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //获取状态栏的高度
            int statusBarHeight = getStatusBarHeight(getActivity());
            //将顶部空间的top padding设置为和状态栏一样的高度，以此达到预期的效果
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }




//    @OnClick(R.id.iv_monitoring_videoNotice)
//    public void onViewClicked() {
//    }

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
