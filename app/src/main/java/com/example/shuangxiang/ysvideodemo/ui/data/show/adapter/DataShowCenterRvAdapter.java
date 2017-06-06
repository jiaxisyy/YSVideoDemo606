package com.example.shuangxiang.ysvideodemo.ui.data.show.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shuangxiang.ysvideodemo.R;

import java.util.List;
import java.util.Random;

/**
 * Created by shuang.xiang on 2017/5/18.
 */

public class DataShowCenterRvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<String> names;
    private MyItemClickListener mItemClickListener;

    public void setItemClickListener(MyItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public DataShowCenterRvAdapter(Context context, List<String> names) {
        mContext = context;
        this.names = names;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_show_monitoring_center,
                parent, false);
        return new MyViewHolder(inflate,mItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        if (names != null && names.size() > 0) {
            viewHolder.mTitle.setText(names.get(position));
            int i = new Random().nextInt(4);
            switch (i) {
                case 0:
                    viewHolder.mPic.setImageResource(R.drawable.icon_yali_big);
                    break;
                case 1:
                    viewHolder.mPic.setImageResource(R.drawable.icon_wendu_big);
                    break;
                case 2:
                    viewHolder.mPic.setImageResource(R.drawable.icon_fuhe_big);
                    break;
                case 3:
                    viewHolder.mPic.setImageResource(R.drawable.icon_jienengliang_big);
                    break;
            }

        }


    }

    @Override
    public int getItemCount() {
        return names == null ? 0 : names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mTitle;
        ImageView mPic;
        MyItemClickListener mItemClickListener;

        public MyViewHolder(View itemView, MyItemClickListener mItemClickListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.mItemClickListener = mItemClickListener;
            mTitle = (TextView) itemView.findViewById(R.id.tv_item_monitoring_centerName);
            mPic = (ImageView) itemView.findViewById(R.id.iv_item_monitoring_centerPic);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.itemClick(view, getPosition());

            }

        }
    }

    public interface MyItemClickListener {
        void itemClick(View view, int position);
    }
}
