package com.example.shuangxiang.ysvideodemo.ui.home.product.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.shuangxiang.ysvideodemo.R;
import com.example.shuangxiang.ysvideodemo.common.Constants;
import com.example.shuangxiang.ysvideodemo.common.utils.CacheUtils;
import com.example.shuangxiang.ysvideodemo.ui.home.product.bean.ProductInfo;

import java.util.List;

/**
 * Created by shuang.xiang on 2017/4/24.
 */

public class HomeProductAdapter extends RecyclerView.Adapter {
    private List<ProductInfo.ListBean> mList;
    private Context mContext;

    public HomeProductAdapter(List<ProductInfo.ListBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_home_product, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.mTitle.setText(mList.get(position).getName().toString());
        viewHolder.mDetail.setText(mList.get(position).getDescription().toString());
        String url = Constants.Define.BASE_URL + mList.get(position).getImgPath();
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader("Cookie",
                CacheUtils.getString
                        (mContext, Constants.Define.COOKIE))
                .build());
        Glide.with(mContext).load(glideUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(viewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTitle;
        TextView mDetail;


        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_item_homeProduct_pic);
            mTitle = (TextView) itemView.findViewById(R.id.tv_item_homeProduct_title);
            mDetail = (TextView) itemView.findViewById(R.id.tv_item_homeProduct_detail);
        }
    }
}
