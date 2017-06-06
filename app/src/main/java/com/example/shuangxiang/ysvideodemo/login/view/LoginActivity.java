package com.example.shuangxiang.ysvideodemo.login.view;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.CustomToast;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.login.presenter.LoginPresenter;
import com.example.shuangxiang.ysvideodemo.manager.ActivityManager;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;
import com.videogo.openapi.EZOpenSDK;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit.User;



/**
 * Created by shuang.xiang on 2017/4/6.
 */

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.et_login_userName)
    EditText mEtLoginUserName;
    @BindView(R.id.et_login_passWord)
    EditText mEtLoginPassWord;
    @BindView(R.id.cb_login_isRemember)
    CheckBox mCbLoginIsRemember;
    @BindView(R.id.cb_login_showPassword)
    CheckBox mCbLoginShowPassword;
    @BindView(R.id.btn_login_login)
    Button mBtnLoginLogin;
    private boolean mChecked;
    private ProgressDialog mProgressDialog;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        //当页面更布局有背景时使用可以全屏观看
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initSomething() {
       ActivityManager.getInstance().addActivity(new WeakReference<Activity>(this));
        if (CacheUtils.getString(this, Constants.Define.USERNAME) != null && !CacheUtils.getString
                (this, Constants.Define.USERNAME).equals("") && CacheUtils.getString(this,
                Constants.Define.PASSWORD) != null && !CacheUtils.getString(this, Constants.Define
                .PASSWORD).equals("")) {
            String username = CacheUtils.getString(this, Constants.Define.USERNAME);
            String password = CacheUtils.getString(this, Constants.Define.PASSWORD);
            mEtLoginUserName.setText(username);
            mEtLoginPassWord.setText(password);
        }
        //监听密码状态
        mCbLoginShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //如果选中，显示密码
                    showPassWord();
                } else {
                    //否则隐藏密码
                    hidePassWord();
                }


            }
        });


    }

    @Override
    public String getUserName() {
        if (mEtLoginUserName.getText().toString() != null && !mEtLoginUserName.getText().toString()
                .contains(" ")) {
            return mEtLoginUserName.getText().toString().trim();

        }
        return "userNameError";

    }

    @Override
    public String getPassWord() {
        if (mEtLoginPassWord.getText().toString() != null && !mEtLoginPassWord.getText().toString()
                .contains(" ")) {
            return mEtLoginPassWord.getText().toString().trim();

        }
        return "passWordError";
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        if(mProgressDialog!=null&&mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void clearUserName() {

    }

    @Override
    public void clearPassWord() {

    }

    @Override
    public void toMainActivity(User user) {
        LoginPresenter loginPresenter = new LoginPresenter(this, this, mChecked);
        mProgressDialog = new ProgressDialog(this);
        showLoading();
        loginPresenter.login(user);
    }

    @Override
    public boolean isRememberPassWord() {
        return false;
    }

    @Override
    public void showFailedError(String error) {
        CustomToast.showToast(this, error, Toast.LENGTH_SHORT);
    }

    @Override
    public void showPassWord() {
        mEtLoginPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }

    @Override
    public void hidePassWord() {
        mEtLoginPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @OnClick(R.id.btn_login_login)
    public void onViewClicked() {

        //有效点击
        if (Utils.isValidClick()) {
            if (getUserName().equals("userNameError") || getPassWord().equals("passWordError")) {
                showFailedError("输入有误,请重新输入");
            }
            //是否记住密码
            mChecked = mCbLoginIsRemember.isChecked();
            toMainActivity(new User(getUserName(), getPassWord()));
        }
    }

    //点击两次退出
    private long firstTime = 0;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) { //如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else { //两次按键小于2秒时，退出应用
//                    logoutEZO();
                    ActivityManager.getInstance().exit();
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    /**
     *
     * 退出萤石云登录账号
     */
    private void logoutEZO() {
        final EZOpenSDK instance = EZOpenSDK.getInstance();
        new Thread(new Runnable() {
            @Override
            public void run() {
                instance.logout();
            }
        }).start();
    }
}
