package com.example.shuangxiang.ysvideodemo.download.presenter;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CustomToast;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.download.bean.AppMessage;
import com.example.shuangxiang.ysvideodemo.download.model.DownloadModel;
import com.example.shuangxiang.ysvideodemo.download.model.IDownloadModel;
import com.example.shuangxiang.ysvideodemo.download.view.IDownloadView;
import com.example.shuangxiang.ysvideodemo.service.MyIntentService;

/**
 * Created by shuang.xiang on 2017/4/12.
 */

public class DownloadPresernter implements IDownloadPresenter {
    private IDownloadView mDownloadView;
    private IDownloadModel mDownloadModel;
    private Context mContext;
    private TextView mMessage;
    private TextView mCancel;
    private TextView mSure;
    private PopupWindow mPopupWindow;

    public DownloadPresernter(IDownloadView downloadView, Context context) {
        mDownloadView = downloadView;
        mDownloadModel = new DownloadModel(this);
        mContext = context;
    }

    @Override
    public void startDownload() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_all, null);
        TextView title = (TextView) view.findViewById(R.id.tv_dialog_allTitle);
        mMessage = (TextView) view.findViewById(R.id.tv_dialog_allMessage);
        mCancel = (TextView) view.findViewById(R.id.tv_dialog_allCancel);
        mSure = (TextView) view.findViewById(R.id.tv_dialog_allSure);
        title.setText(R.string.pop_updateVersion);
        mMessage.setText(R.string.pop_updateMessage);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDownloadView.hintUpdatePop(mPopupWindow);
            }
        });
        mSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDownloadView.hintUpdatePop(mPopupWindow);
                //展示下载进度条
                //下载后的保存路径
                CustomToast.showToast(mContext,Constants.Define.STARTDOWNLOADFILE, Toast
                        .LENGTH_SHORT);
                String apkPath = Environment.getExternalStorageDirectory().getPath() + "/kawa.apk";
                Log.d("TEST","apkPath->"+apkPath);
                MyIntentService.startUpdateService(mContext, "", apkPath);
            }
        });
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout
                .LayoutParams.MATCH_PARENT, false);
        if (mPopupWindow.isShowing()) {
            mDownloadView.hintUpdatePop(mPopupWindow);
        }
        mPopupWindow.setFocusable(true);
        //下面的是设置外部是否可以点击
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.showAsDropDown(view, 0, 0, Gravity.FILL);


    }

    @Override
    public void stopDownload() {

    }

    @Override
    public void getAppMessageSucceed(AppMessage message) {
        int versionCode = Utils.getVersionCode(mContext);
        if (message.getVersionCode() > versionCode) {
            //执行更新
            mDownloadView.hintNewestVersion();
        } else {
            mDownloadView.showNewestVersion();

        }
    }

    @Override
    public void getAppMessageFailed() {
//        mDownloadView.showUpdateMessage(Constants.Define.UPDATE_FAILED);
        mDownloadView.showNewestVersion();
    }

    @Override
    public void checkVersion() {
        mDownloadModel.getAppMessage();
    }

}
