package com.example.shuangxiang.ysvideodemo.ui.myself.p;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.login.view.LoginActivity;
import com.example.shuangxiang.ysvideodemo.ui.myself.v.IMyselfFragmentV;

/**
 * Created by shuang.xiang on 2017/5/3.
 */

public class MyselfFragmentP implements IMyselfFragmentP {
    private IMyselfFragmentV mView;
    private Context mContext;
    private TextView mMessage;
    private TextView mCancel;
    private TextView mSure;
    private PopupWindow mPopupWindow;

    public MyselfFragmentP(IMyselfFragmentV view, Context context) {
        mView = view;
        mContext = context;
    }

    @Override
    public void exit() {

        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_all, null);
        TextView title = (TextView) view.findViewById(R.id.tv_dialog_allTitle);
        mMessage = (TextView) view.findViewById(R.id.tv_dialog_allMessage);
        mCancel = (TextView) view.findViewById(R.id.tv_dialog_allCancel);
        mSure = (TextView) view.findViewById(R.id.tv_dialog_allSure);
        title.setText(R.string.pop_exit);
        mMessage.setText(R.string.pop_isExit);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.dismissPop(mPopupWindow);
            }
        });
        mSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.exit();

                mContext.startActivity(new Intent(mContext, LoginActivity.class));
            }
        });
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout
                .LayoutParams.MATCH_PARENT, false);
        if (mPopupWindow.isShowing()) {
            mView.dismissPop(mPopupWindow);
        }
        mPopupWindow.setFocusable(true);
        //下面的是设置外部是否可以点击
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.showAsDropDown(view, 0, 0, Gravity.FILL);


    }

}
