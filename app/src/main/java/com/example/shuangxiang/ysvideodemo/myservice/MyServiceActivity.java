package com.example.shuangxiang.ysvideodemo.myservice;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.manager.ActivityManager;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by shuang.xiang on 2017/4/13.
 */

public class MyServiceActivity extends BaseActivity implements IMyService {
    @BindView(R.id.tb_myservice)
    Toolbar mTbMyservice;
    @BindView(R.id.tv_callPhone)
    TextView mTvCallPhone;
    private PopupWindow mPopupWindow;
    private TextView mMessage;
    private TextView mCancel;
    private TextView mSure;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void initContentView(Bundle savedInstanceState) {

        setContentView(R.layout.activity_myservice);
    }

    @Override
    protected void initSomething() {
        ActivityManager.getInstance().addActivity(new WeakReference<Activity>(this));
        mTbMyservice.setNavigationIcon(R.drawable.icon_back);
        mTbMyservice.setTitle("");
        setSupportActionBar(mTbMyservice);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.tv_callPhone)
    public void onViewClicked() {
        showPopupWindow();


    }


    @Override
    public void showPopupWindow() {

        View view = LayoutInflater.from(this).inflate(R.layout.pop_all, null);
        TextView title = (TextView) view.findViewById(R.id.tv_dialog_allTitle);
        mMessage = (TextView) view.findViewById(R.id.tv_dialog_allMessage);
        mCancel = (TextView) view.findViewById(R.id.tv_dialog_allCancel);
        mSure = (TextView) view.findViewById(R.id.tv_dialog_allSure);
        title.setText(R.string.pop_callPhone);
        mMessage.setText(R.string.pop_phoneNum);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone();
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout
                .LayoutParams.MATCH_PARENT, false);
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
        mPopupWindow.setFocusable(true);
        //下面的是设置外部是否可以点击
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.showAsDropDown(view, 0, 0, Gravity.FILL);


    }


    @Override
    public void finishActivity() {

    }

    @Override
    public void callPhone() {
        String number = mMessage.getText().toString().trim();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .CALL_PHONE)) {
                // 返回值：
                //如果app之前请求过该权限,被用户拒绝, 这个方法就会返回true.
                //如果用户之前拒绝权限的时候勾选了对话框中”Don’t ask again”的选项,那么这个方法会返回false.
                //如果设备策略禁止应用拥有这条权限, 这个方法也返回false.
                // 弹窗需要解释为何需要该权限，再次请求授权

                // 帮跳转到该应用的设置界面，让用户手动授权
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                // 不需要解释为何需要该权限，直接请求授权
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
            }
        } else {
            // 已经获得授权，可以打电话
            CallPhone(number);
        }
    }

    private void CallPhone(String num) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        //url:统一资源定位符
        //uri:统一资源标示符（更广）
        intent.setData(Uri.parse("tel:" + num));
        //开启系统拨号器
        startActivity(intent);
    }


}
