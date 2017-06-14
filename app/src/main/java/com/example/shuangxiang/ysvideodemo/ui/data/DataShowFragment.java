package com.example.shuangxiang.ysvideodemo.ui.data;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.CustomToast;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.data.analyze.DataAnalyzeFragment;
import com.example.shuangxiang.ysvideodemo.ui.data.show.adapter.DataShowBottomRvAdapter;
import com.example.shuangxiang.ysvideodemo.ui.data.show.adapter.DataShowCenterRvAdapter;
import com.example.shuangxiang.ysvideodemo.ui.data.show.adapter.SpacesItemDecoration;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.p.SettingParameterP;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.v.ISettingParameterV;
import com.example.shuangxiang.ysvideodemo.ui.warning.WarningActivity;

import org.reactivestreams.Subscription;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;

/**
 * Created by shuang.xiang on 2017/5/2.
 */

public class DataShowFragment extends BaseFragment implements ISettingParameterV {
    @BindView(R.id.iv_monitoring_notice)
    ImageView mIvMonitoringNotice;
    @BindView(R.id.tb_data_monitoring)
    Toolbar mTbDataMonitoring;
    @BindView(R.id.iv_datashow_analyze)
    ImageView mIvDatashowAnalyze;
    @BindView(R.id.tv_dataShow_circleTitle)
    TextView mTvDataShowCircleTitle;
    @BindView(R.id.tv_dataShow_circleNum)
    TextView mTvDataShowCircleNum;
    @BindView(R.id.tv_dataShow_titleIn_Tb)
    TextView mTbTile;
    @BindView(R.id.tv_dataShow_circleUnit)
    TextView mTvDataShowCircleUnit;
    @BindView(R.id.ll_dataShow_circle)
    ImageView mLlDataShowCircle;
    @BindView(R.id.rv_dataMonitoring_showBottom)
    RecyclerView mRvBottom;
    @BindView(R.id.rv_dataMonitoring_showCenter)
    RecyclerView mRvCenter;

    private Subscription mSubscription;
    private Disposable mDisposable;
    private CompositeDisposable compositeDisposable;
    private SettingParameterP mSettingParameterP;
    private DataShowBottomRvAdapter mAdapterBottom;
    private GridLayoutManager mLayoutManagerBottom;
    private LinearLayoutManager mLayoutManagerCenter;
    private DataShowCenterRvAdapter mRvAdapterCenter;
    private boolean mFirstInto = true;
    private ProgressDialog mProgressDialog;
    //默认设置圆的值
    private int mDefaultPosition = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_data_show;
    }

    @Override
    protected void init() {
//        if (getActivity() != null) {
//            mProgressDialog = new ProgressDialog(getActivity());
//            mProgressDialog.show();
//        }

        mTbDataMonitoring.setNavigationIcon(R.drawable.icon_mydevice_back);
        mTbDataMonitoring.setTitle("");
        setImmerseLayout(mTbDataMonitoring);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mTbDataMonitoring);
        setHasOptionsMenu(true);

        String title = CacheUtils.getString(getActivity(), Constants.Define.MYDEVICE_TO_SECONDHOME_TBTITLE);
        if (title != null && !title.equals("")) {
            mTbTile.setText(title);
        }
    }


    @Override
    protected void initData() {
        Animation operatingAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.datashow_circle_into);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        if (operatingAnim != null) {
            mLlDataShowCircle.startAnimation(operatingAnim);
        }
        if (mFirstInto && mSettingParameterP == null) {
            mSettingParameterP = new SettingParameterP(this, getActivity());
            mSettingParameterP.getTitle("MONITOR");
        }
    }

    @Override
    protected boolean isCache() {
        return true;
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


    @OnClick({R.id.iv_monitoring_notice, R.id.iv_datashow_analyze})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_monitoring_notice:

                startActivity(new Intent(getActivity(), WarningActivity.class));

                break;
            case R.id.iv_datashow_analyze:
                Utils.replace(getActivity().getSupportFragmentManager(), R.id.fl_home2,
                        DataAnalyzeFragment.class);
                break;
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


    @Override
    public void setRvData(final List<String> names, final List<String> values, List<String> ids,
                          final List<String> units, List<String> defaultAddress) {

        if (names != null && names.size() > 0 && values != null && values.size() > 0 && ids != null && ids.size()
                > 0 && units != null && units.size() > 0) {

            if (mFirstInto) {
                mFirstInto = false;
                mLayoutManagerBottom = new GridLayoutManager(getActivity(), 2);
                mRvBottom.setHasFixedSize(true);
                mLayoutManagerBottom.setAutoMeasureEnabled(true);
                mRvBottom.setLayoutManager(mLayoutManagerBottom);
                mAdapterBottom = new DataShowBottomRvAdapter(getActivity(), names, values, units);

                mRvBottom.setAdapter(mAdapterBottom);
                //***************************************
                //***************************************
                //***************************************
                mLayoutManagerCenter = new LinearLayoutManager(getActivity(),
                        LinearLayoutManager.HORIZONTAL, false);
                mRvCenter.setHasFixedSize(true);
                mLayoutManagerCenter.setAutoMeasureEnabled(true);
                mRvCenter.setLayoutManager(mLayoutManagerCenter);
                mRvAdapterCenter = new DataShowCenterRvAdapter(getActivity(), names);
                mRvCenter.addItemDecoration(new SpacesItemDecoration(Constants.Define.DATASHOW_CENTER_SPACINGINPIXELS));
                mRvCenter.setAdapter(mRvAdapterCenter);

                mTvDataShowCircleTitle.setText(names.get(mDefaultPosition));
                mTvDataShowCircleNum.setText(values.get(mDefaultPosition));
                mTvDataShowCircleUnit.setText("单位: " + units.get(mDefaultPosition));
                mRvAdapterCenter.setItemClickListener(new DataShowCenterRvAdapter.MyItemClickListener() {
                    @Override
                    public void itemClick(View view, int position) {
                        mDefaultPosition = position;
                        mTvDataShowCircleTitle.setText(names.get(position));
                        mTvDataShowCircleNum.setText(values.get(position));
                        mTvDataShowCircleUnit.setText("单位: " + units.get(position));
                    }
                });
                if (mTvDataShowCircleTitle != null && mTvDataShowCircleNum != null && mTvDataShowCircleUnit != null) {
                    mTvDataShowCircleTitle.setText(names.get(mDefaultPosition));
                    mTvDataShowCircleNum.setText(values.get(mDefaultPosition));
                    Log.d("TEST", "dataShow_refresh");
                    mTvDataShowCircleUnit.setText("单位: " + units.get(mDefaultPosition));
                }
            } else {
                mAdapterBottom.setValues(values);
            }
        } else {
            if (mFirstInto) {
                mFirstInto = false;
                CustomToast.showToast(getActivity(), Constants.Define.SERVERDATAERROR, Toast.LENGTH_SHORT);
                Log.d("TEST", "else");
            }
        }
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            mProgressDialog.dismiss();
//        }
    }

    @Override
    public void setToast(String s) {

    }

    @Override
    public void dissDialog() {
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            mProgressDialog.dismiss();
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSettingParameterP.dispose();
    }
}
