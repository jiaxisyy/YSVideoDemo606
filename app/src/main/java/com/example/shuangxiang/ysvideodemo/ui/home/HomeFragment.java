package com.example.shuangxiang.ysvideodemo.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.SecondHomeActivity;
import com.example.shuangxiang.ysvideodemo.ui.home.banner.presenter.HomeFragmentPresenter;
import com.example.shuangxiang.ysvideodemo.ui.home.banner.view.IHomeFragmentView;
import com.example.shuangxiang.ysvideodemo.ui.home.product.adapter.HomeProductAdapter;
import com.example.shuangxiang.ysvideodemo.ui.home.product.bean.ProductInfo;
import com.example.shuangxiang.ysvideodemo.ui.home.product.presenter.HomeProductPresenter;
import com.example.shuangxiang.ysvideodemo.ui.home.product.view.IHomeProductView;
import com.example.shuangxiang.ysvideodemo.ui.mydevice.MyDeviceActivity;
import com.example.shuangxiang.ysvideodemo.ui.warning.WarningActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;


/**
 * Created by shuang.xiang on 2017/4/19.
 */

public class HomeFragment extends BaseFragment implements IHomeFragmentView, IHomeProductView {
    private HomeFragmentPresenter mPresenter;
    private HomeProductPresenter mProductPresenter;
    @BindView(R.id.tb_home)
    Toolbar mTbHome;
    @BindView(R.id.banner_home)
    ConvenientBanner mBannerHome;
    @BindView(R.id.ll_home_myDevice)
    RelativeLayout mLlHomeMyDevice;
    @BindView(R.id.ll_home_monitoring)
    RelativeLayout mLlHomeMonitoring;
    @BindView(R.id.ll_home_warning)
    RelativeLayout mLlHomeWarning;
    @BindView(R.id.ll_home_setting)
    RelativeLayout mLlHomeSetting;
    @BindView(R.id.rv_home_product)
    RecyclerView mRvHomeProduct;
    private ProgressDialog mProgressDialog;
    private GridLayoutManager mLayoutManager;
    @Override
    protected int getLayoutId() {


        return R.layout.fragment_home;
    }

    public HomeFragment() {
    }

    //定义一个静态私有变量(不初始化，不使用final关键字，使用volatile保证了多线程访问时instance变量的可见性，避免了instance初始化时其他变量属性还没赋值完时，被另外线程调用)
    private static volatile HomeFragment instance;

    //定义一个共有的静态方法，返回该类型实例
    public static HomeFragment getInstance() {
        if (instance == null) {
            synchronized (HomeFragment.class) {
                if (instance == null) {
                    instance = new HomeFragment();
                }
            }
        }
        return instance;
    }

    @Override
    protected void init() {
        setImmerseLayout(mTbHome);
        setBanner();
        mProductPresenter = new HomeProductPresenter(this, getActivity());
        mProductPresenter.load();
        Log.d("TEST", "load");

    }


    @Override
    protected void initData() {

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

    @OnClick({R.id.ll_home_myDevice, R.id.ll_home_monitoring, R.id.ll_home_warning, R.id.ll_home_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_home_myDevice:

                startActivity(new Intent(getActivity(), MyDeviceActivity.class));

                break;
            case R.id.ll_home_monitoring:
                Intent intentM = new Intent(getActivity(), SecondHomeActivity.class);
                intentM.putExtra("flag", "monitoring");
                startActivity(intentM);
                break;
            case R.id.ll_home_warning:
                CacheUtils.putString(getActivity(), Constants.Define.MYDEVICE_TO_SECONDHOME_ID, "");
                CacheUtils.putString(getActivity(), Constants.Define.MYDEVICE_TO_SECONDHOME_TBTITLE, "");
                startActivity(new Intent(getActivity(), WarningActivity.class));
                break;
            case R.id.ll_home_setting:
                Intent intentS = new Intent(getActivity(), SecondHomeActivity.class);
                intentS.putExtra("flag", "setting");
                startActivity(intentS);
                break;
        }
    }

    @Override
    public void setBanner() {
        mPresenter = new HomeFragmentPresenter(this, getActivity());
        mPresenter.loadBanner();
    }

    @Override
    public void setBannersListUrl(List<String> list) {

        if(mBannerHome!=null){
            mBannerHome.setPages(new CBViewHolderCreator<NetworkGlideView>() {
                @Override
                public NetworkGlideView createHolder() {
                    return new NetworkGlideView();
                }
            }, list).setPageIndicator(new int[]{R.drawable.yuan_dangqian, R.drawable.yuan_default});
            mBannerHome.startTurning(5000);
        }


    }

    @Override
    public void setProductResouce(List<ProductInfo.ListBean> list) {

        if(mLayoutManager==null){
            mLayoutManager = new GridLayoutManager(getActivity(), 2) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            mLayoutManager.setAutoMeasureEnabled(true);
        }
        if(mRvHomeProduct!=null){
            mRvHomeProduct.setLayoutManager(mLayoutManager);
            mRvHomeProduct.setAdapter(new HomeProductAdapter(list, getActivity()));
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        mBannerHome.startTurning(5000);
    }

    @Override
    public void onPause() {
        super.onPause();
        mBannerHome.stopTurning();
    }
    //    // 开始自动翻页
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //开始自动翻页
//        convenientBanner.startTurning(5000);
//    }
//
//    // 停止自动翻页
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //停止翻页
//        convenientBanner.stopTurning();
//    }


}
