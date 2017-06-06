package com.example.shuangxiang.ysvideodemo.feedback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.utils.CustomToast;
import com.example.shuangxiang.ysvideodemo.feedback.bean.FeedbackInfo;
import com.example.shuangxiang.ysvideodemo.feedback.p.FeedbackP;
import com.example.shuangxiang.ysvideodemo.feedback.v.IFeedBackV;
import com.example.shuangxiang.ysvideodemo.manager.ActivityManager;
import com.example.shuangxiang.ysvideodemo.ui.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by shuang.xiang on 2017/4/12.
 */

public class FeedbackActivity extends BaseActivity implements IFeedBackV {
    private FeedbackP mPresenter;
    @BindView(R.id.tv_feedback_submit)
    TextView mTvFeedbackSubmit;
    @BindView(R.id.et_feedback_inputQuestion)
    EditText mEtFeedbackInputQuestion;
    @BindView(R.id.et_feedback_inputPhone)
    EditText mEtFeedbackInputPhone;
    @BindView(R.id.iv_feedback_picAdd)
    ImageView mIvFeedbackPicAdd;
    @BindView(R.id.tv_feedback_wordNum)
    TextView mTvFeedbackWordNum;
    @BindView(R.id.tb_feedback)
    Toolbar mToolbar;
    private static int REQUESTCODE = 1;
    private Uri mUri;
    private ProgressDialog mProgressDialog;
    private String mPath;
    private PopupWindow mPopupWindow;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_feedback);
    }

    @Override
    protected void initSomething() {
        ActivityManager.getInstance().addActivity(new WeakReference<Activity>(this));
        mToolbar.setNavigationIcon(R.drawable.icon_back);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getFeedbackMessage();

    }


    @OnClick({R.id.tv_feedback_submit, R.id.iv_feedback_picAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_feedback_submit:
                mPresenter = new FeedbackP(this);
                if ( getFeedbackMessage().equals("")
                        || getFeedbackPhone().equals("")) {
                    CustomToast.showToast(this, "请填写完整", Toast.LENGTH_SHORT);
                } else if(mPath==null){
                    mPresenter.submit(new FeedbackInfo(getFeedbackMessage(),getFeedbackPhone()));
                }else if(mPath!=null&&!mPath.equals("")){
                    mPresenter.uploadFile(new File(mPath));
                }
                break;
            case R.id.iv_feedback_picAdd:
//                Intent intent = new Intent();
//                /* 开启Pictures画面Type设定为image */
//                intent.setType("image/*");
//                /* 使用Intent.ACTION_GET_CONTENT这个Action */
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                /* 取得相片后返回本画面 */
//                startActivityForResult(intent, REQUESTCODE);
                Intent picture = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(picture, REQUESTCODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUESTCODE) {

            //不选取图片直接取消的处理
            if (data == null) {
                return;
            }
            mUri = data.getData();
            ContentResolver cr = this.getContentResolver();
            //****************************************************
            //获取图片的路径：

            String[] proj = {MediaStore.Images.Media.DATA};

            //好像是android多媒体数据库的封装接口，具体的看Android文档
            Cursor cursor = managedQuery(mUri, proj, null, null, null);
            //按我个人理解 这个是获得用户选择的图片的索引值
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            //将光标移至开头 ，这个很重要，不小心很容易引起越界
            cursor.moveToFirst();
            //最后根据索引值获取图片路径
            mPath = cursor.getString(column_index);
            Log.d("TEST", "path->" + mPath);


            //****************************************************
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(cr.openInputStream(mUri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ImageView imageView = (ImageView) findViewById(R.id.iv_feedback_picAdd);
                /* 将Bitmap设定到ImageView */
            imageView.setImageBitmap(bitmap);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void showDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_feedback, null);
        TextView sure = (TextView) view.findViewById(R.id.tv_dialog_feedback_sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
                finish();
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
    public void showProgressBar() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.show();
    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void hideProgressBar() {
        if(mProgressDialog!=null){
            mProgressDialog.dismiss();
        }


    }

    @Override
    public String getFeedbackPhone() {

        return mEtFeedbackInputPhone.getText().toString();
    }

    @Override
    public String getFeedbackMessage() {
        return mEtFeedbackInputQuestion.getText().toString();
    }

    @Override
    public void showToast(String s) {

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
