package com.example.shuangxiang.ysvideodemo.ui.about;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.manager.ActivityManager;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;
import com.example.shuangxiang.ysvideodemo.ui.about.kawa.AboutKawaActivity;
import com.example.shuangxiang.ysvideodemo.ui.about.privacy.PrivacyActivity;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;

/**
 * Created by shuang.xiang on 2017/5/3.
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.tb_about)
    Toolbar mTbAbout;
    @BindView(R.id.tv_about_aboutKawa)
    TextView mTvAboutKawa;
    @BindView(R.id.tv_about_privacy)
    TextView mTvPrivacy;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_about);
    }

    @Override
    protected void initSomething() {
        ActivityManager.getInstance().addActivity(new WeakReference<Activity>(this));
        setImmerseLayout(mTbAbout);
        mTbAbout.setNavigationIcon(R.drawable.icon_back);
        mTbAbout.setTitle("");
        setSupportActionBar(mTbAbout);
    }


    @OnClick({R.id.tv_about_aboutKawa, R.id.tv_about_privacy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_about_aboutKawa:
                startActivity(new Intent(this, AboutKawaActivity.class));
                break;
            case R.id.tv_about_privacy:
                startActivity(new Intent(this, PrivacyActivity.class));
                break;
        }
    }

    protected void setImmerseLayout(View view) {
        //先将状态栏透明化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //获取状态栏的高度
            int statusBarHeight = getStatusBarHeight(this);
            //将顶部空间的top padding设置为和状态栏一样的高度，以此达到预期的效果
            view.setPadding(0, statusBarHeight, 0, 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
