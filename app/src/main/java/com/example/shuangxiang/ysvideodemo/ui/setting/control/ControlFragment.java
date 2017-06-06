package com.example.shuangxiang.ysvideodemo.ui.setting.control;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.CustomToast;
import com.example.shuangxiang.ysvideodemo.common.utils.Utils;
import com.example.shuangxiang.ysvideodemo.ui.BaseFragment;
import com.example.shuangxiang.ysvideodemo.ui.setting.adapter.ControlRvAdapter;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.ParameterFragment;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.p.SettingParameterP;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.v.ISettingParameterV;
import com.example.shuangxiang.ysvideodemo.ui.warning.WarningActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zhy.autolayout.utils.ScreenUtils.getStatusBarHeight;

/**
 * Created by shuang.xiang on 2017/5/17.
 */

public class ControlFragment extends BaseFragment implements ISettingParameterV {
    @BindView(R.id.iv_control_notice)
    ImageView mIvNotice;
    @BindView(R.id.tb_setting_control)
    Toolbar mTb;
    @BindView(R.id.iv_control_toParameter)
    ImageView mIvControlToParameter;
    @BindView(R.id.rv_setting_control)
    RecyclerView mRv;
    @BindView(R.id.tv_control_TbTitle)
    TextView mTbTitle;
    private ControlRvAdapter mAdapter;
    private ProgressDialog mDialog;
    private View mView;
    private Dialog mAdminDialog;
    private EditText mEditTextValue;
    private TextView mTvTextCancel;
    private TextView mTvTextSure;
    private TextView mTvTextTitle;
    private final String digists = "0123456789abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final int SHOWDIALOGINPUT_ADMIN = 1;//管理员弹窗
    private final int SHOWDIALOGINPUT_DEFAULT = 0;//确认控制弹窗
    private SettingParameterP mSettingParameterP;
    private boolean mFirstInto = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_setting_control;
    }

    @Override
    protected void init() {
        mTb.setNavigationIcon(R.drawable.icon_mydevice_back);
        mTb.setTitle("");
        setImmerseLayout(mTb);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mTb);
        setHasOptionsMenu(true);
        String title = CacheUtils.getString(getActivity(), Constants.Define.MYDEVICE_TO_SECONDHOME_TBTITLE);
        if (title != null && !title.equals("")) {
            mTbTitle.setText(title);
        }
    }

    @Override
    protected void initData() {
        mDialog = new ProgressDialog(getActivity());
        mDialog.show();

        if (mFirstInto) {
            mSettingParameterP = new SettingParameterP(this, getActivity());
            mSettingParameterP.getTitle("CONTROL");
        }
    }

    @Override
    protected boolean isCache() {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.iv_control_notice, R.id.iv_control_toParameter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_control_notice:
                startActivity(new Intent(getActivity(), WarningActivity.class));
                break;
            case R.id.iv_control_toParameter:
                Utils.replace(getFragmentManager(), R.id.fl_home2, ParameterFragment.class);
                break;
        }
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
    public void setRvData(List<String> names, List<String> values, final List<String> ids, List<String>
            units, List<String> defaultAddress) {
        if (names.size() > 0 && values.size() > 0 && ids.size() > 0 && units.size() > 0 && defaultAddress.size() > 0) {
            //默认显示在线

            if (mFirstInto) {
                mFirstInto = false;
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                mRv.setHasFixedSize(true);
                layoutManager.setAutoMeasureEnabled(true);
                mRv.setLayoutManager(layoutManager);
                mAdapter = new ControlRvAdapter(getActivity(), names, values);
                mRv.setAdapter(mAdapter);
                mAdapter.setMyItemClickListener(new ControlRvAdapter.MyItemClickListener() {
                    @Override
                    public void onItemLongClick(View view, int position, boolean isChecked) {
                        Log.d("TEST", "setRvData->position=" + position);
                        Log.d("TEST", "isChecked=" + isChecked);
                        String elementId = ids.get(position);
                        Log.d("TEST", "elementId=" + elementId);
                        String deviceid = CacheUtils.getString(getActivity(), Constants.Define
                                .MYDEVICE_TO_SECONDHOME_ID);
                        Log.d("TEST", "deviceid=" + deviceid);
                        String url = Constants.Define
                                .BASE_URL + "devices/" + deviceid + "/elements/" + elementId + "?client=app";
                        Log.d("TEST", "url=" + url);
                        showAdminDialog(position, String.valueOf(!isChecked), url, "",
                                SHOWDIALOGINPUT_ADMIN);
                        //第一次不传密码
                    }
                });
            } else {

                mAdapter.setValues(values);


            }
        } else {
            if (mFirstInto) {
                mFirstInto=false;
                CustomToast.showToast(getActivity(), Constants.Define.SERVERDATAERROR, Toast.LENGTH_SHORT);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSettingParameterP.dispose();
    }

    @Override
    public void setToast(String s) {
        CustomToast.showToast(getActivity(), s, Toast.LENGTH_SHORT);

    }

    @Override
    public void dissDialog() {
//        if (mAdminDialog.isShowing()) {
//            mAdminDialog.dismiss();
//        }
        if (mDialog.isShowing()&&mDialog!=null) {
            mDialog.dismiss();
        }

    }

    /**
     * 展示输入框
     */
    private void showAdminDialog(final int position, final String value, final String url, final String
            password, final int showType) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_input, null, false);
        // 创建自定义样式的Dialog
        mAdminDialog = new Dialog(getActivity(), R.style.input_dialog);
        // 设置返回键无效
        mAdminDialog.setCancelable(false);
        mAdminDialog.setCanceledOnTouchOutside(true);
        mAdminDialog.setContentView(mView, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        mEditTextValue = (EditText) mView.findViewById(R.id.et_dialog_input_value);
        mTvTextCancel = (TextView) mView.findViewById(R.id.tv_dialog_inputCancel);
        mTvTextSure = (TextView) mView.findViewById(R.id.tv_dialog_inputSure);
        mTvTextTitle = (TextView) mView.findViewById(R.id.tv_dialog_inputTitle);
        if (showType == SHOWDIALOGINPUT_ADMIN) {
            mEditTextValue.setKeyListener(DigitsKeyListener.getInstance(digists));
            mTvTextTitle.setText(R.string.dialog_inputAdminTitle);
        } else if (showType == SHOWDIALOGINPUT_DEFAULT) {
            mTvTextTitle.setText(R.string.dialog_setValueTitle);
            mEditTextValue.setText(R.string.dialog_confirm);
            mEditTextValue.setEnabled(false);
        }

        mAdminDialog.show();
        mTvTextCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdminDialog.dismiss();
            }
        });
        mTvTextSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showType == SHOWDIALOGINPUT_ADMIN) {
                    String password = mEditTextValue.getText().toString();
                    String adminPassword = CacheUtils.getString(getActivity(), Constants.Define
                            .ADMINPASSWORD);
                    if (adminPassword != null && !adminPassword.equals("")) {//和本地密码做比较,以后改为网络形式
                        if (password.equals(adminPassword)) {
                            //是管理员
                            mAdminDialog.dismiss();
                            //弹出输入数据的
                            showAdminDialog(position, value, url, password, SHOWDIALOGINPUT_DEFAULT);
                        } else {
                            CustomToast.showToast(getActivity(), Constants.Define.ADMINPASSWORDERROR, Toast
                                    .LENGTH_SHORT);
                        }
                    }

                } else if (showType == SHOWDIALOGINPUT_DEFAULT) {
                    //默认的输入框
                    //拿取设置的值
                    if (!mDialog.isShowing()) {
                        mDialog.show();
                    }
                    mSettingParameterP.setValue(url, value, password);

                }


            }
        });

    }

}
