package com.example.shuangxiang.ysvideodemo.ui.setting.parameter.p;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.common.utils.CustomToast;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.bean.ParameterInfo;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.m.ISettingParameterM;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.m.SettingParameterM;
import com.example.shuangxiang.ysvideodemo.ui.setting.parameter.v.ISettingParameterV;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by shuang.xiang on 2017/5/18.
 */

public class SettingParameterP implements ISettingParameterP {
    private ISettingParameterM mISettingParameterM;
    private ISettingParameterV mISettingParameterV;

    private Context mContext;
    private String mMTitleId;
    private String mDatatemplateid;
    private List<String> mFieldNames;
    private List<String> mNames;
    private List<String> mIds;
    private List<String> mUnits;
    private List<String> mDefaultAddress;


    public SettingParameterP(ISettingParameterV ISettingParameterV, Context context) {
        mISettingParameterM = new SettingParameterM(this);
        mISettingParameterV = ISettingParameterV;
        mContext = context;
    }

    @Override
    public void getTitleSucceed(ParameterInfo[] parameterInfo) {
        if (parameterInfo.length > 0) {
            List<ParameterInfo.ElementsBean> elements = parameterInfo[0].getElements();
            mFieldNames = new ArrayList<>();
            int size = elements.size();
            mNames = new ArrayList<>();
            mIds = new ArrayList<>();
            mUnits = new ArrayList<>();
            mDefaultAddress = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                String name = elements.get(i).getName();
                String fieldName = elements.get(i).getFieldName();
                String id = elements.get(i).getId();
                String unit = elements.get(i).getUnit();
                String defaultAddress = elements.get(i).getDefaultAddress();

                mFieldNames.add(fieldName);
                mIds.add(id);
                mNames.add(name);
                mUnits.add(unit);
                mDefaultAddress.add(defaultAddress);
                Log.d("TEST", "name=" + name);
            }
            //设备最新数据
            String valueUrl = Constants.Define.BASE_URL + "dataTemplates/" + mDatatemplateid + "/datas?pageSize=1&showTable=false&deviceId=" + mMTitleId;
            Log.d("TEST", "valueUrl=" + valueUrl);
            mISettingParameterM.getParameterValue(valueUrl);
        } else {
            CustomToast.showToast(mContext, Constants.Define.SERVERDATAERROR, Toast.LENGTH_SHORT);
            mISettingParameterV.dissDialog();
        }
    }

    @Override
    public void getTitle(String type) {
        mMTitleId = CacheUtils.getString(mContext, Constants.Define.MYDEVICE_TO_SECONDHOME_ID);
        mDatatemplateid = CacheUtils.getString(mContext, Constants.Define.MYDEVICE_TO_SECONDHOME_DATATEMPLATEID);
        //获取元件类别列表
        String url = Constants.Define.BASE_URL + "devices/" + mMTitleId +
                "/elementCategorys?type=" + type;
        Log.d("TEST", "url=" + url);
        mISettingParameterM.getParameterTitle(url);
    }

    @Override
    public void getValueSucceed(String s) {

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray(Constants.Define.JSONARRAYNAME);
            List<String> values = new ArrayList<>();
            int size = mFieldNames.size();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                for (int j = 0; j < size; j++) {
                    String value = object.getString(mFieldNames.get(j));
                    values.add(value);
                }
            }
            mISettingParameterV.setRvData(mNames, values, mIds, mUnits, mDefaultAddress);
            mISettingParameterV.dissDialog();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ERROR", e.getMessage().toString());
        }
    }

    @Override
    public void setValue(String url, String value, String password) {
        String json = "{\"value\":" + value + ",\"password\": \"" + password + "\"}";
        Log.d("TEST", "setValue->json=" + json);
//        String s = new Gson().toJson(new ParameterRequestInfo(value, password));
//        Log.d("TEST", "GsonToJson=" + s);
        mISettingParameterM.setParameterValue(url, json);
    }

    @Override
    public void setValueSucceed(String s) {
        mISettingParameterV.setToast("设置成功");
        mISettingParameterV.dissDialog();
    }

    @Override
    public void setValueFailed(String s) {
        try {
            JSONObject object = new JSONObject(s);
            String error = object.getString("error");
            mISettingParameterV.setToast(error);
            mISettingParameterV.dissDialog();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(String s) {
        mISettingParameterV.setToast(s);
        mISettingParameterV.dissDialog();
    }


    @Override
    public void dispose() {
        CompositeDisposable compositeDisposable = mISettingParameterM.onDestroy();

        if(compositeDisposable!=null){
            compositeDisposable.dispose();
        }

    }
}
