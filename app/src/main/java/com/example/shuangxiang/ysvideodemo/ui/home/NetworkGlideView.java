package com.example.shuangxiang.ysvideodemo.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;

/**
 * Created by shuang.xiang on 2017/4/21.
 */

public class NetworkGlideView implements Holder<String> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(final Context context, int position, final String data) {
        Log.d("TEST", "NetworkGlideView->UpdateUI");

        GlideUrl glideUrl = new GlideUrl(data, new LazyHeaders.Builder()
                .addHeader("Cookie", CacheUtils.getString(context, Constants.Define
                        .COOKIE))
                .build());
//        imageView.setImageResource(R.drawable.ic_default_adimage);
        Glide.with(context).load(glideUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);

    }
}
