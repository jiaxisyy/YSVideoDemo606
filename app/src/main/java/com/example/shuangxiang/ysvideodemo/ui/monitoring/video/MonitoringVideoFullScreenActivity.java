package com.example.shuangxiang.ysvideodemo.ui.monitoring.video;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageButton;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;
import com.videogo.exception.BaseException;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZDeviceInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.videogo.camera.CameraInfo.VIDEO_LEVEL_HD;

/**
 * Created by shuang.xiang on 2017/5/31.
 */

public class MonitoringVideoFullScreenActivity extends BaseActivity implements SurfaceHolder.Callback {
    private static final String TAG = "TEST";
    @BindView(R.id.realplay_sv_video_fullScreen)
    SurfaceView mFullScreen;
    @BindView(R.id.ib_video_exit)
    ImageButton mIbExit;
    private EZOpenSDK mInstance;
    private SurfaceHolder mRealPlaySh = null;
    private EZPlayer mMEZPlayer;
    private int mErrorCode = -1;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activiti_video_full_screen);
    }

    @Override
    protected void initSomething() {
        mInstance = EZOpenSDK.getInstance();

        mRealPlaySh = mFullScreen.getHolder();
        mRealPlaySh.addCallback(this);
        startVideo();

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


    @OnClick(R.id.ib_video_exit)
    public void onViewClicked() {
        finish();
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
}
