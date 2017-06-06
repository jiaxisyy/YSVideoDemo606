package com.example.shuangxiang.ysvideodemo.ui.monitoring;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.monitoring.flow.MonitoringFlowFragment;
import com.example.shuangxiang.ysvideodemo.ui.monitoring.main.MonitoringMainFragment;
import com.example.shuangxiang.ysvideodemo.ui.monitoring.video.MonitoringVideoFragment;
import com.example.shuangxiang.ysvideodemo.ui.warning.WarningActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;

/**
 * Created by shuang.xiang on 2017/5/25.
 */

public class MonitoringFragment extends BaseFragment {
    @BindView(R.id.tv_monitoring_Title)
    TextView mTvMonitoringTitle;
    @BindView(R.id.iv_monitoring_Notice)
    ImageView mIvMonitoringNotice;
    @BindView(R.id.tb_monitoring)
    Toolbar mTb;
    @BindView(R.id.tab_monitoring)
    TabLayout mTabMonitoring;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_monitoring;
    }

    @Override
    protected void init() {
        mTb.setNavigationIcon(R.drawable.icon_back);
        mTb.setTitle("");
        setImmerseLayout(mTb);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mTb);
        setHasOptionsMenu(true);
        List<String> tb_titles = new ArrayList<>();
        //添加标题
        tb_titles.add(Constants.Define.MONITORING_MAIN.toString());
        tb_titles.add(Constants.Define.MONITORING_FLOW.toString());
        tb_titles.add(Constants.Define.MONITORING_VIDEO.toString());
        mTabMonitoring.setLayoutMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < tb_titles.size(); i++) {
            mTabMonitoring.addTab(mTabMonitoring.newTab().setText(tb_titles.get(i)));
        }
        String title = CacheUtils.getString(getActivity(), Constants.Define.MYDEVICE_TO_SECONDHOME_TBTITLE);
        if (title != null && !title.equals("")) {
            mTvMonitoringTitle.setText(title);
        }
        initView();

    }
    private void initView() {

//        //设置默认界面列表
        Utils.replace(getActivity().getSupportFragmentManager(), R.id.fl_monitoring,
                MonitoringMainFragment.class);
        mTabMonitoring.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if(position==0){
                    Utils.replace(getActivity().getSupportFragmentManager(), R.id.fl_monitoring,
                            MonitoringMainFragment.class);
                }else if(position==1){
                    Utils.replace(getActivity().getSupportFragmentManager(), R.id.fl_monitoring,
                            MonitoringFlowFragment.class);
                }else if(position==2){
                    Utils.replace(getActivity().getSupportFragmentManager(), R.id.fl_monitoring,
                            MonitoringVideoFragment.class);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean isCache() {
        return false;
    }



    @OnClick(R.id.iv_monitoring_Notice)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), WarningActivity.class));
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
