package com.example.shuangxiang.ysvideodemo.ui.myself;

import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.download.presenter.DownloadPresernter;
import com.example.shuangxiang.ysvideodemo.download.view.IDownloadView;
import com.example.shuangxiang.ysvideodemo.feedback.FeedbackActivity;
import com.example.shuangxiang.ysvideodemo.myservice.MyServiceActivity;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.about.AboutActivity;
import com.example.shuangxiang.ysvideodemo.ui.myself.p.MyselfFragmentP;
import com.example.shuangxiang.ysvideodemo.ui.myself.v.IMyselfFragmentV;
import com.videogo.openapi.EZOpenSDK;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;

/**
 * Created by shuang.xiang on 2017/4/19.
 */

public class MyselfFragment extends BaseFragment implements IMyselfFragmentV, IDownloadView {
    @BindView(R.id.tb_myself)
    Toolbar mTbMyself;
    @BindView(R.id.rl_myself_myservice)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.rl_myself_feedback)
    RelativeLayout mRlMyselfFeedback;
    @BindView(R.id.tv_myself_versionName)
    TextView mTvVersionName;
    @BindView(R.id.tv_myself_newest)
    TextView mTvNewest;
    @BindView(R.id.rl_myself_update)
    RelativeLayout mRlUpdate;
    private MyselfFragmentP mPresenter;
    private DownloadPresernter mDownloadPresernter;

    public MyselfFragment() {
    }

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile MyselfFragment instance;

    //定义一个共有的静态方法，返回该类型实例
    public static MyselfFragment getInstance() {
        if (instance == null) {
            synchronized (MyselfFragment.class) {
                if (instance == null) {
                    instance = new MyselfFragment();
                }
            }
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myself;
    }

    @Override
    protected void init() {
        setImmerseLayout(mTbMyself);
    }

    @Override
    protected void initData() {
        String versionName = Utils.getVersionName(getActivity());
        mTvVersionName.setText("V" + versionName);
        mDownloadPresernter = new DownloadPresernter(this, getActivity());
        mDownloadPresernter.checkVersion();
    }

    @Override
    protected boolean isCache() {
        return true;
    }

    @OnClick(R.id.rl_myself_myservice)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), MyServiceActivity.class));
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


    @OnClick(R.id.rl_myself_feedback)
    public void onViewClickedFeedback() {
        startActivity(new Intent(getActivity(), FeedbackActivity.class));
    }

    @OnClick(R.id.rl_myself_about)
    public void onViewClickedAbout() {
        startActivity(new Intent(getActivity(), AboutActivity.class));
    }

    @OnClick(R.id.tv_myself_exit)
    public void onViewClickedExit() {
        mPresenter = new MyselfFragmentP(this, getActivity());
        mPresenter.exit();

    }


    @Override
    public void showPop(PopupWindow popupWindow) {


    }

    @Override
    public void dismissPop(PopupWindow popupWindow) {
        popupWindow.dismiss();
    }

    @Override
    public void exit() {
        logoutEZO();
        getActivity().finish();
    }

    /**
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

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showUpdateMessage(String message) {

    }

    @Override
    public void showNewestVersion() {
        if (mTvNewest != null && mRlUpdate != null){
            mTvNewest.setVisibility(View.VISIBLE);
            mRlUpdate.setClickable(false);
        }
    }

    @Override
    public void hintNewestVersion() {
        if (mTvNewest != null && mRlUpdate != null) {
            mTvNewest.setVisibility(View.INVISIBLE);
            mRlUpdate.setClickable(true);
        }
    }

    @Override
    public void setVersionName(String versionName) {
    }

    @Override
    public void showUpdatePop(PopupWindow popupWindow) {

    }

    @Override
    public void hintUpdatePop(PopupWindow popupWindow) {
        popupWindow.dismiss();
    }


    @OnClick(R.id.rl_myself_update)
    public void rl_myself_update() {
        if(mRlUpdate.isClickable()){
            mDownloadPresernter.startDownload();
        }

    }
}
