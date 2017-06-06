package com.example.shuangxiang.ysvideodemo.ui.monitoring.video;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.videogo.exception.BaseException;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZDeviceInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.videogo.camera.CameraInfo.VIDEO_LEVEL_HD;

/**
 * Created by shuang.xiang on 2017/5/20.
 */

public class MonitoringVideoFragment extends BaseFragment implements SurfaceHolder.Callback {
    private static final String TAG = "TEST";
    //    @BindView(R.id.tv_monitoring_videoTitle)
//    TextView mTvTitle;
//    @BindView(R.id.iv_monitoring_videoNotice)
//    ImageView mIvNotice;
//    @BindView(R.id.tb_monitoring_video)
//    Toolbar mTb;
    @BindView(R.id.realplay_sv_video)
    SurfaceView mRealPlaySv;
    @BindView(R.id.iv_monitoring_fullScreen)
    ImageView mIvFullScreen;

    private int mErrorCode = -1;
    private EZOpenSDK mInstance;
    private SurfaceHolder mRealPlaySh = null;
    private EZPlayer mMEZPlayer;
    private boolean mFirstInfo = true;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_monitoring_video;
    }

    @Override
    protected void init() {
        //海康威视
        mInstance = EZOpenSDK.getInstance();
        mInstance.openLoginPage();
        mRealPlaySh = mRealPlaySv.getHolder();
        mRealPlaySh.addCallback(this);
        if (mFirstInfo) {
            mFirstInfo = false;
            startVideo();
        }

    }


    @Override
    protected void initData() {

    }

    @Override
    protected boolean isCache() {
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TEST", "onPause");
        if (mMEZPlayer != null) {
            mMEZPlayer.pausePlayback();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TEST", "onResume");
        if (mMEZPlayer != null) {
            mMEZPlayer.resumePlayback();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("TEST", "onStop");
        if (mMEZPlayer != null) {
            mMEZPlayer.stopPlayback();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mMEZPlayer != null) {
            mMEZPlayer.startRealPlay();
        }
    }

    private void startVideo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<EZDeviceInfo> result = null;
                    result = mInstance.getDeviceList(0, 10);
                    EZDeviceInfo mDeviceInfo = result.get(0);
                    String deviceSerial = mDeviceInfo.getDeviceSerial();
                    int cameraNum = mDeviceInfo.getCameraNum();
                    mMEZPlayer = mInstance.createPlayer(deviceSerial, cameraNum);
//                  mMEZPlayer.setHandler(mHandler);
                    mInstance.setVideoLevel(deviceSerial, cameraNum, VIDEO_LEVEL_HD);
                    mMEZPlayer.setSurfaceHold(mRealPlaySh);
                    mMEZPlayer.startRealPlay();
                } catch (BaseException e) {
                    mErrorCode = e.getErrorCode();
                    Log.e(TAG, mErrorCode + "");
                }
            }
        }).start();
    }
//
//    protected void setImmerseLayout(View view) {
//        //先将状态栏透明化
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getActivity().getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //获取状态栏的高度
//            int statusBarHeight = getStatusBarHeight(getActivity());
//            //将顶部空间的top padding设置为和状态栏一样的高度，以此达到预期的效果
//            view.setPadding(0, statusBarHeight, 0, 0);
//        }
//    }


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
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (mMEZPlayer != null) {
            mMEZPlayer.setSurfaceHold(surfaceHolder);
        }
        mRealPlaySh = surfaceHolder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }


    @OnClick(R.id.iv_monitoring_fullScreen)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), MonitoringVideoFullScreenActivity.class));
    }
}
